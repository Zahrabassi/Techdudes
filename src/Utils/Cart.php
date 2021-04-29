<?php
/**
 * Cart class is used to manage the shopping items.
 */

namespace App\Utils;

use Symfony\Component\HttpFoundation\Session\SessionInterface;

/**
 * Class Cart
 *
 * @package ShopBundle\Utils
 */
class Cart implements \ArrayAccess
{
    /** book category id */
    const CATEGORY_CHILD_BOOK = 2;

    /** child category max count to apply discount */
    const CHILD_BOOK_MAX_COUNT = 5;

    /** ecach book category max count to apply discount */
    const EACH_CATEGORY_MAX_COUNT = 10;

    /** @var SessionInterface */
    protected $session;

    /** @var string */
    protected $key;

    /** @var string */
    protected $coupon = null;

    /** @var integer */
    protected $couponDiscount = 15;

    /** @var integer */
    protected $lineDiscount = 5;

    /** @var integer */
    protected $discount = 10;

    /** @var array */
    protected $appliedDiscounts = [];

    /**
     * @param SessionInterface $session
     * @param string $key
     */
    public function __construct(SessionInterface $session, $key = '_cart')
    {
        $this->session = $session;
        $this->key = $key;
    }

    /**
     * @param int $id Id of the Item to retrieve
     * @return Item|null The given Item or null if not found.
     */
    public function getItem($id)
    {
        return $this->session->get($this->key . '/' . $id);
    }

    /**
     * Get all Items
     *
     * @return CartItem[] An array of Items,  if no Items then an empty array
     */
    public function getItems()
    {
        return $this->session->get($this->key, []);
    }

    /**
     * Adds an Item to the cart
     *
     * @param CartItem $newItem The Item to add
     */
    public function addItem($newItem)
    {
        $newItemId = $newItem->getId();
        if ($this->session->has($this->key . '/' . $newItemId)) {
            $oldItem = $this->session->get($this->key . '/' . $newItemId);
            $newQty = $oldItem->getQuantity() + $newItem->getQuantity();
            $newItem->setQuantity($newQty);
        }
        $this->setItem($newItem);

        $this->session->set('totalPrice', $this->getDiscountTotal());
    }

    /**
     * Adds multiple Items to the cart
     *
     * @param ItemInterface[] $items Array of Items to add
     */
    public function addItems(array $items)
    {
        foreach ($items as $item) {
            $this->addItem($item);
        }
    }

    /**
     * Sets an Item, if there is all ready an Item with same id, it will be replaced.
     *
     * @param CartItem $item
     */
    public function setItem($item)
    {
        if ($item->isValid() === false) {
            throw new \InvalidArgumentException('The item is not valid');
        }
        $this->session->set($this->key . '/' . $item->getId(), $item);
    }

    /**
     * Sets a 15% discount coupon to the cart
     *
     * @param String $code
     */
    public function setCoupon($code)
    {
        $this->session->set('coupon', $code);
        $this->session->set('totalPrice', $this->getDiscountTotal());
    }

    /**
     * Return the applied coupon
     */
    public function getCoupon()
    {
        return $this->session->get('coupon');
    }

    /**
     * Return the discount rate
     */
    public function getDiscount()
    {
        return $this->discount;
    }

    /**
     * Return the line discount rate
     */
    public function getLineDiscount()
    {
        return $this->lineDiscount;
    }

    /**
     * Return the coupon discount rate
     */
    public function getCouponDiscount()
    {
        return $this->couponDiscount;
    }

    /**
     * Return the applied coupon
     */
    public function getTotalPrice()
    {
        return $this->session->get('totalPrice');
    }

    /**
     * Sets multiple Items
     *
     * @param array $items An array of Items
     */
    public function setItems(array $items)
    {
        foreach ($items as $item) {
            $this->setItem($item);
        }
    }

    /**
     * Removes an Item from the cart
     *
     * @param int $id Id of the Item to remove
     * @return mixed The removed Item or null if it does not exist
     */
    public function removeItem($id)
    {
        $item = $this->session->remove($this->key . '/' . $id);
        $this->session->set('totalPrice', $this->getDiscountTotal());

        return $item;
    }

    /**
     * @param int $id id of the Item to check
     * @return bool whether the Item is added to the card
     */
    public function hasItem($id)
    {
        return $this->session->has($this->key . '/' . $id);
    }

    /**
     * Count the Items added to the cart
     *
     * @return int The count as an integer.
     */
    public function count()
    {
        return (int)count($this->getItems());
    }

    /**
     * Whether an Item with given id is added to the card
     *
     * @param int $id An id to check for.
     * @return boolean true on success or false on failure.
     */
    public function offsetExists($id)
    {
        return $this->hasItem($id);
    }

    /**
     * Offset to retrieve
     *
     * @param int $id The id of the Item to retrieve.
     * @return ItemInterface|null Returns an ItemInterface or null if not found.
     */
    public function offsetGet($id)
    {
        return $this->getItem($id);
    }

    /**
     * Adds or sets an Item to the cart
     *
     * @param int|null $id If given, any existing Item with same id will be replaced.
     * @param ItemInterface $item The value to set.
     * @return void
     */
    public function offsetSet($id, $item)
    {
        /** @var $item Item */
        if (is_null($id)) {
            $this->addItem($item);
        } elseif ($id === $item->getId()) {
            $this->setItem($item);
        } else {
            throw new \InvalidArgumentException('The index and id of the item must be the same');
        }
    }

    /**
     * Unset the Item with given id
     *
     * @param int $id The id to unset.
     * @return void
     */
    public function offsetUnset($id)
    {
        $this->removeItem($id);
    }

    /**
     * Clears the storage
     */
    public function clear()
    {
        $this->session->remove($this->key);
        $this->session->remove('totalPrice');
        $this->session->remove('coupon');
    }

    /**
     * Get the total price for all Items
     *
     * @return float
     */
    public function getTotal()
    {
        $total = 0;
        foreach ($this->getItems() as $item) {
            $total += $item->getTotal();
        }

        return $total;
    }

    /**
     * Get the book count by category in the cart, if no category all count will be returned
     *
     * @return array
     */
    public function getCategoriesByCount($category = null)
    {
        $categories = [];
        foreach ($this->getItems() as $item) {
            $id = $item->getCategoryId();
            $categories[$id] = isset($categories[$id]) ? $categories[$id] + $item->getQuantity() : $item->getQuantity();
        }

        $result = $categories;
        if (!is_null($category)) {
            if (isset($categories[$category])) {
                $result = $categories[$category];
            } else {
                $result = 0;
            }
        }

        return $result;
    }

    /**
     * Get the total price of category in cart, if no category all categories price will be returned
     *
     * @return array
     */
    public function getPriceByCategory($category = null)
    {
        $priceByCategory = [];

        foreach ($this->getItems() as $item) {
            $id = $item->getCategoryId();
            $itemTotal = $item->getPrice() * $item->getQuantity();
            $priceByCategory[$id] = isset($priceByCategory[$id]) ? $priceByCategory[$id] + $itemTotal : $itemTotal;
        }

        $result = $priceByCategory;
        if (!is_null($category)) {
            if (isset($priceByCategory[$category])) {
                $result = $priceByCategory[$category];
            } else {
                $result = 0;
            }
        }

        return $result;
    }

    /**
     * Get the total price for all Items with defined discount for coupon, line discount, book discount
     *
     * @return float
     */
    public function getDiscountTotal()
    {
        $total = $this->getTotal();
        $discountTotal = $total;

        if ($this->getCoupon() == null) {
            $discountPrice = $this->getDiscountPrice(self::CATEGORY_CHILD_BOOK, self::CHILD_BOOK_MAX_COUNT, $this->discount);
            if ($discountPrice > 0) { // child category book more than 5 will get a discount for total of its kind
                $discountTotal = $total - $discountPrice;
                $this->appliedDiscounts[] = $this->discount;
            }

            if (@min($this->getCategoriesByCount()) >= self::EACH_CATEGORY_MAX_COUNT) {
                //all categories has more than 10 items in the cart so apply additional line discount
                $this->appliedDiscounts[] = $this->lineDiscount;
                $discountTotal = $discountTotal - ($discountTotal * ($this->lineDiscount / 100));
            }
        } else {
            // apply coupon discount
            $this->appliedDiscounts[] = $this->couponDiscount;
            $discountTotal = $this->getCoupon() === null ? $total : $total - ($total * ($this->couponDiscount / 100));
        }

        return number_format((float)$discountTotal, 2, '.', '');
    }

    /**
     *  Apply discount to category if max count reached
     *
     * @return float
     */
    public function getDiscountPrice($category, $count, $discountRate)
    {
        $discount = 0;
        $bookCountByCategory = $this->getCategoriesByCount($category);
        $priceByCategory = $this->getPriceByCategory($category);

        if ($bookCountByCategory >= $count) {
            $discount = $priceByCategory * ($discountRate / 100);
        }

        return $discount;
    }

    /**
     * Get the applied discount sum
     *
     * @return array
     */
    public function getAppliedDiscount()
    {
        return array_sum($this->appliedDiscounts);
    }
}