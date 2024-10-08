// main.cpp
#include <iostream>
#include <memory>
#include "../include/Player.h"
#include "../include/Warrior.h"
#include "../include/Item.h"

int main() {
    Player player2("Luca", Warrior());

    // Add items to inventory
    player2.addItemToInventory(std::make_unique<Item>("Sword Shield", "Sword 1"));
    player2.addItemToInventory(std::make_unique<Item>("Shield", "Shield 1"));
    player2.displayInventory();

    player2.removeItemFromInventory("Sword   ");

    std::cout << "\n\n";
    player2.displayInventory();
    return 0;
}