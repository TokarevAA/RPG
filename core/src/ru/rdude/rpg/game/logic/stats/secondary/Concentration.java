package ru.rdude.rpg.game.logic.stats.secondary;


import ru.rdude.rpg.game.logic.stats.Calculatable;
import ru.rdude.rpg.game.logic.stats.Stat;
import ru.rdude.rpg.game.logic.stats.primary.*;

public class Concentration extends Stat implements Calculatable {

    private boolean calculatable;
    private Int intel;
    private Lvl lvl;
    private Dex dex;
    private Agi agi;
    private Vit vit;
    private Str str;

    public Concentration(double value) {
        super(value);
        this.calculatable = false;
    }

    public Concentration(double value, Int intel, Dex dex, Agi agi, Vit vit, Str str, Lvl lvl) {
        super(value);
        this.calculatable = true;
        this.intel = intel;
        this.lvl = lvl;
        this.dex = dex;
        this.agi = agi;
        this.vit = vit;
        this.str = str;
        intel.subscribe(this);
        lvl.subscribe(this);
        dex.subscribe(this);
        agi.subscribe(this);
        vit.subscribe(this);
        str.subscribe(this);
    }

    @Override
    public double calculate() {
        if (!calculatable) return value();
        double INT = intel.value();
        double LVL = lvl.value();
        double DEX = dex.value();
        double AGI = agi.value();
        double VIT = vit.value();
        double STR = str.value();
        this.set(1 + Math.floor((INT + LVL)/3) + Math.floor(DEX/4) + Math.floor((AGI + VIT + STR)/9));
        return value();
    }
}
