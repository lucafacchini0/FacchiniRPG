#pragma once
#include "Statistics.h"
#include <cstdint>

typedef std::uint16_t stats_t;
typedef std::uint16_t level_t;

class Character {
public:
    virtual ~Character() = default;

    [[nodiscard]] virtual const Statistics& getHP() const = 0;
    [[nodiscard]] virtual const Statistics& getMana() const = 0;
    [[nodiscard]] virtual const Statistics& getAttack() const = 0;
    [[nodiscard]] virtual const Statistics& getDefense() const = 0;

    [[nodiscard]] virtual stats_t getHPIncrement(level_t level) const = 0;
    [[nodiscard]] virtual stats_t getManaIncrement(level_t level) const = 0;
    [[nodiscard]] virtual stats_t getAttackIncrement(level_t level) const = 0;
    [[nodiscard]] virtual stats_t getDefenseIncrement(level_t level) const = 0;


    // Theory
    // The problem is that, in the Playe class, when the Player levels up, we don't know what
    // statistic to increment, because we don't know which class the Player is. We can't just
    // increment all the statistics, because we don't know if the Player is a Warrior, Mage, etc.
    // For this reason, I created the Character class. Let's imagine it as a class that has the
    // role of being overridden by each class (Warrior, Mage etc.). These single classes will
    // return their increment values, so that the Player class can increment the correct statistic.
};