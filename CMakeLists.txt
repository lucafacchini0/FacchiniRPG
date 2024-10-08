cmake_minimum_required(VERSION 3.28)
project(FacchiniRPG)

set(CMAKE_CXX_STANDARD 17)

add_executable(FacchiniRPG
        main.cpp
        Statistics.h
        Player.h
        Warrior.h
        Character.h
        Colors.h
        Item.h
        Inventory.h
        nlohmann/json.h
)
