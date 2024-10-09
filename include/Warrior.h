#pragma once
#include "Character.h"

class Warrior : public Character {
public:
    static constexpr stats_t DEFAULT_HP_INCREMENT = 20;
    static constexpr stats_t DEFAULT_MANA_INCREMENT = 10;
    static constexpr  stats_t DEFAULT_ATK_INCREMENT = 5;
    static constexpr  stats_t DEFAULT_DEFENSE_INCREMENT = 3;

    Warrior() : HP(DEFAULT_HP_INCREMENT, DEFAULT_HP_INCREMENT),
                Mana(DEFAULT_MANA_INCREMENT, DEFAULT_MANA_INCREMENT),
                Attack(DEFAULT_ATK_INCREMENT, DEFAULT_ATK_INCREMENT),
                Defense(DEFAULT_DEFENSE_INCREMENT, DEFAULT_DEFENSE_INCREMENT) {}

    // Implementation of the Character pure virtual functions
    [[nodiscard]] const Statistics& getHP() const override { return HP; }
    [[nodiscard]] const Statistics& getMana() const override { return Mana; }
    [[nodiscard]] const Statistics& getAttack() const override { return Attack; }
    [[nodiscard]] const Statistics& getDefense() const override { return Defense; }

    // Methods will be called on levelup
    [[nodiscard]] stats_t getHPIncrement(level_t level) const override { return DEFAULT_HP_INCREMENT; }
    [[nodiscard]] stats_t getManaIncrement(level_t level) const override { return DEFAULT_MANA_INCREMENT; }
    [[nodiscard]] stats_t getAttackIncrement(level_t level) const override { return DEFAULT_ATK_INCREMENT; }
    [[nodiscard]] stats_t getDefenseIncrement(level_t level) const override { return DEFAULT_DEFENSE_INCREMENT; }

private:
    Statistics HP;
    Statistics Mana;
    Statistics Attack;
    Statistics Defense;
};