#pragma once
#include <cstdint>

typedef std::uint16_t stats_t;

class Statistics {
public:
    // Default constructor
    Statistics() : Current(0), Max(0) {}

    // Constructor with initial values
    Statistics(stats_t Current, stats_t Max) {
        if (Current > Max) {
            this->Current = Max;
            this->Max = Max;
        } else {
            this->Current = Current;
            this->Max = Max;
        }
    }

    [[nodiscard]] stats_t getCurrent() const { return Current; }
    [[nodiscard]] stats_t getMax() const { return Max; }

    // Set current value
    bool setCurrent(stats_t new_current_value) {
        if (new_current_value > Max) {
            return false;
        }
        Current = new_current_value;
        return true;
    }

    // Set max value
    bool setMax(stats_t new_max_value) {
        if (new_max_value < 1) {
            return false;
        }
        Max = new_max_value;
        if (Current > Max) {
            Current = Max;
        }
        return true;
    }

    // Decrement current value
    bool decrement(stats_t amount) {
        if (Current < amount) {
            Current = 0;
            return false;
        }
        Current -= amount;
        return true;
    }

    // Increment current value
    bool increment(stats_t amount) {
        if (Current + amount > Max) {
            Current = Max;
            return false;
        }
        Current += amount;
        return true;
    }

private:
    stats_t Current; // Current value
    stats_t Max;     // Max value
};