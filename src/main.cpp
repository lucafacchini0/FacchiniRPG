// src/main.cpp
#include <iostream>
#include <memory>
#include "../include/Player.h"
#include "../include/Warrior.h"
#include "../include/Item.h"

int main() {
    Player player("Luca", Warrior());
    player.printStats();

    return 0;
}
