#pragma once
#include <iostream>
#include <string>
#include <cstdint>
#include <memory>
#include "Inventory.h"
#include "Statistics.h"
#include "Warrior.h"
#include "Colors.h"

typedef std::uint64_t exp_t;
typedef std::uint16_t level_t;

using std::string;

class Player {
public:
    // **************************************** //
    // ************ Constructors ************** //
    // **************************************** //

    Player()
        : Player("Default") {}

    // No class specified
    explicit Player(string name)
        : Player(std::move(name), Warrior()) {

        className = "Unemployed";
    }


    // Warrior class
    Player(string name, const Warrior& warrior)
        : name(std::move(std::move(name))),
          EXP{0, EXP_BASE},
          Level{1},
          HP(std::make_unique<Statistics>(warrior.getHP())),
          Mana(std::make_unique<Statistics>(warrior.getMana())),
          Attack(std::make_unique<Statistics>(warrior.getAttack())),
          Defense(std::make_unique<Statistics>(warrior.getDefense())) {

        className = "Warrior";
    }

    // **************************************** //
    // ************ Add EXP ****************** //
    // **************************************** //

    // Add experience points and handle leveling up
    void addEXP(exp_t amount) {
        EXP.Current += amount; // Add EXP

        // If the EXP gained is sufficient for two levels, but the player would reach the maximum level,
        // set the maximum level. DONT ELIMINATE EXP
        if (EXP.Current >= EXP.Required && Level.Current == MaxLevel - 1) {
            EXP.Current = EXP.Required;
            return;
        }

        while (EXP.Current >= EXP.Required) {
            if(Level.Current >= MaxLevel) {
                EXP.Required = 0;
                EXP.Current = 0;
                break;
            }
            levelUp();
        }
    }


    // **************************************** //
    // ************ Set Level **************** //
    // **************************************** //

    // Set player level
    void setLevel(level_t new_level) {
        if (new_level > MaxLevel) {
            Level.Current = MaxLevel;
        } else {
            Level.Current = new_level;
        }
        EXP.Current = 0;
        EXP.Required = RequiredEXPFormula();
    }


    // **************************************** //
    // ************ Print Stats ************** //
    // **************************************** //

    void printStats() const {
        static constexpr std::uint8_t LENGTH = 30;
        std::uint16_t paddingName = ((LENGTH - name.length()) / 2);
        std::uint16_t paddingClass = ((LENGTH - className.length()) / 2);

        std::cout << BLUE;
        for(int i = 0; i < LENGTH; i++) { std::cout << "_"; }
        std::cout << RESET << std::endl << std::endl;

        // Print the name centered
        std::cout << std::string(paddingName, ' ') << YELLOW << name << std::endl;

        // Print the class centered
        std::cout << std::string(paddingClass, ' ') << BOLDYELLOW << className << std::endl << std::endl;

        // Print stats
        std::cout << BLUE << "*" << MAGENTA << " EXP: " << RESET << EXP.Current << "/" << EXP.Required << std::endl;
        std::cout << BLUE << "*" << MAGENTA << " Level: " << RESET << Level.Current << std::endl;
        std::cout << BLUE << "*" << MAGENTA << " HP: " << RESET << HP->getCurrent() << "/" << HP->getMax() << std::endl;
        std::cout << BLUE << "*" << MAGENTA << " Mana: " << RESET << Mana->getCurrent() << "/" << Mana->getMax() << std::endl;
        std::cout << BLUE << "*" << MAGENTA << " Attack: " << RESET << Attack->getCurrent() << std::endl;
        std::cout << BLUE << "*" << MAGENTA << " Defense: " << RESET << Defense->getCurrent() << std::endl;

        std::cout << std::endl;
        std::cout << BLUE;
        for(int i = 0; i < LENGTH; i++) { std::cout << "_"; }
        std::cout << RESET << std::endl;
    }

    // **************************************** //
    // ************ Inventory **************** //
    // **************************************** //

    void addItemToInventory(std::unique_ptr<Item> itemName) {
        inventory.addItem(std::move(itemName));
    }

    void removeItemFromInventory(const string& itemName) {
        inventory.removeItem(itemName);
    }

    void displayInventory() const {
        inventory.displayItems();
    }

    // **************************************** //
    // ************ Getters/Setters *********** //
    // **************************************** //

    // Get methods
    [[nodiscard]] const string& getName() const { return name; }
    [[nodiscard]] const Statistics& getHP() const { return *HP; }
    [[nodiscard]] const Statistics& getMana() const { return *Mana; }
    [[nodiscard]] const Statistics& getAttack() const { return *Attack; }
    [[nodiscard]] const Statistics& getDefense() const { return *Defense; }
    [[nodiscard]] level_t getLevel() const { return Level.Current; }

    // Setters
    // Set Current
    void setCurrentHP(stats_t new_hp) const { HP->setCurrent(new_hp); }
    void setCurrentMana(stats_t new_mana) const { Mana->setCurrent(new_mana); }
    void setCurrentAttack(stats_t new_attack) const { Attack->setCurrent(new_attack); }
    void setCurrentDefense(stats_t new_defense) const { Defense->setCurrent(new_defense); }

    // setMax
    void setMaxHP(stats_t new_hp) const { HP->setMax(new_hp); }
    void setMaxMana(stats_t new_mana) const { Mana->setMax(new_mana); }
    void setMaxAttack(stats_t new_attack) const { Attack->setMax(new_attack); }
    void setMaxDefense(stats_t new_defense) const { Defense->setMax(new_defense); }

    // Decrement
    void decrementHP(stats_t amount) const { HP->decrement(amount); }
    void decrementMana(stats_t amount) const { Mana->decrement(amount); }
    void decrementAttack(stats_t amount) const { Attack->decrement(amount); }
    void decrementDefense(stats_t amount) const { Defense->decrement(amount); }

    // Increment
    void incrementHP(stats_t amount) const { HP->increment(amount); }
    void incrementMana(stats_t amount) const { Mana->increment(amount); }
    void incrementAttack(stats_t amount) const { Attack->increment(amount); }
    void incrementDefense(stats_t amount) const { Defense->increment(amount); }


private:
    // **************************************** //
    // ************ Variables/Consts ********** //
    // **************************************** //

    // Player information
    const string name;
    string className;

    // EXP information
    struct EXP_Struct {
        exp_t Current;
        exp_t Required;
    };

    EXP_Struct EXP; // Player's EXP
    static constexpr exp_t EXP_BASE = 2; // Base experience for level calculation

    // Level information
    struct Level_Struct {
        level_t Current;
    };

    Level_Struct Level; // Player's level
    static constexpr level_t MaxLevel = 64; // Maximum level

    // Inventory
    Inventory inventory;
    std::uint8_t inventorySize = 10;

    // **************************************** //
    // ************ Player Stats ************** //
    // **************************************** //

    // Player's statistics
    std::unique_ptr<Statistics> HP;
    std::unique_ptr<Statistics> Mana;
    std::unique_ptr<Statistics> Attack;
    std::unique_ptr<Statistics> Defense;


    // **************************************** //
    // ************ Private Methods *********** //
    // **************************************** //

    // Level up the player
    void levelUp() {

        EXP.Current -= EXP.Required;
        Level.Current++;
        EXP.Required = RequiredEXPFormula();
        updateStatistics(); // Update player stats on level up
    }

    // Update player statistics on level up
    void updateStatistics() const {
        HP->setMax(Warrior::DEFAULT_HP_INCREMENT * Level.Current);
        Mana->setMax(Warrior::DEFAULT_MANA_INCREMENT * Level.Current);
        Attack->setMax(Warrior::DEFAULT_ATK_INCREMENT * Level.Current);
        Defense->setMax(Warrior::DEFAULT_DEFENSE_INCREMENT * Level.Current);

        HP->setCurrent(HP->getMax());
        Mana->setCurrent(Mana->getMax());
        Attack->setCurrent(Warrior::DEFAULT_ATK_INCREMENT * Level.Current);
        Defense->setCurrent(Warrior::DEFAULT_DEFENSE_INCREMENT * Level.Current);
    }

    // Calculate required EXP for the next level
    [[nodiscard]] exp_t RequiredEXPFormula() const {
        return EXP_BASE * Level.Current * Level.Current - 4 * Level.Current + 4;
    }
};
