#include <vector>
#include <memory>
#include <fstream>
#include <iostream>
#include <algorithm>
#include "Item.h"

class Inventory {
public:
    void addItem(std::unique_ptr<Item> item) {
        items.push_back(std::move(item)); // push back is used to add an element to the end of the vector
                                            // move is used to transfer ownership of the unique_ptr to the vector
    }


    /**
     * @brief Removes an item from the inventory based on the provided name.
     *
     * This function uses the `std::remove_if` algorithm to identify items
     * to be removed from the inventory. The `remove_if` function iterates
     * through the vector of unique pointers to `Item` objects and applies
     * a lambda function that compares the name of each item with the
     * provided name (`itemName`). Items that satisfy this condition (i.e.,
     * their name matches `itemName`) will be "removed" from the vector
     * (i.e., they will be moved to the end of the vector without being
     * actually deleted at this stage).
     *
     * After `remove_if` has shifted the items to be removed to the end
     * of the vector, the `erase` method is called to physically remove
     * those items from the vector. `erase` takes as arguments the
     * iterator pointing to the beginning and the iterator pointing to
     * the end of the range to be removed, which is determined by
     * `remove_if`.
     *
     * This approach is efficient because it minimizes the number of
     * element shifts within the vector and leverages C++ iterator
     * semantics to handle the removal.
     *
     * @param itemName The name of the item to be removed from the inventory.
     */
    void removeItem(const std::string& itemName) {
        items.erase(std::remove_if(items.begin(), items.end(),
            [&itemName](const std::unique_ptr<Item>& item) {
                return item->getName() == itemName; // Compare the item's name
            }), items.end()); // Remove the matching items
    }

    void displayItems() const {
        for (const std::unique_ptr<Item>& item : items) {
            std::cout << item->getName() << ": " << item->getDescription() << std::endl;
        }
    }
private:
    std::vector<std::unique_ptr<Item>> items;
};