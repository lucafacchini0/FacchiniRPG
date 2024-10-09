// Item.h
#pragma once
#include <string>

class Item {
public:
    Item(std::string name, std::string description)
        : name(std::move(name)), description(std::move(description)) {}

    [[nodiscard]] const std::string& getName() const { return name; }
    [[nodiscard]] const std::string& getDescription() const { return description; }

protected:
    std::string name;
    std::string description;
};
