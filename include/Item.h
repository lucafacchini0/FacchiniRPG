#pragma once
#include <string>

using std::string;

class Item {
public:
    Item(string name, string description)
        : name(std::move(name)), description(std::move(description)) {}

    [[nodiscard]] const string& getName() const { return name; }
    [[nodiscard]] const string& getDescription() const { return description; }

private:
    string name;
    string description;
};