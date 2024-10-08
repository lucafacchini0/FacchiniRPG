// main.cpp
#include "Player.h"
#include "Warrior.h"
#include "Item.h"
#include <iostream>

int main() {
    Player player2("Luca", Warrior());

    // Add items to inventory
    player2.addItemToInventory(std::unique_ptr<Item>(new Item("Sword Shield", "Sword 1")));
    player2.addItemToInventory(std::unique_ptr<Item>(new Item("Shield", "Shield 1")));
    player2.displayInventory();

    player2.removeItemFromInventory("Sword   ");

    std::cout << "\n\n";
    player2.displayInventory();
    return 0;
}