cmake_minimum_required(VERSION 3.28)
project(FacchiniRPG_Final)

set(CMAKE_CXX_STANDARD 17)

add_executable(FacchiniRPG_Final
        src/main.cpp
        include/Statistics.h
        include/Player.h
        include/Warrior.h
        include/Character.h
        include/Colors.h
        include/Item.h
        include/Inventory.h
        include/Items/Mushrooms.h
)
