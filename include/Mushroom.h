#include "Statistics.h"
#include "Item.h"
#

class Mushroom : public Item {
public:
    Mushroom() : Item("Mushroom", "A mushroom that restores 10 HP") {}

    void use(Player& player) override {
        player.getHP().Current += HP_INCREMENT;
    }

private:
    static const stats_t HP_INCREMENT = 10;
    static const stats_t MANA_INCREMENT = 0;
    static const stats_t ATK_INCREMENT = 0;
    static const stats_t DEFENSE_INCREMENT = 0;

};