package ru.rdude.rpg.game.logic.stats.primary;


import ru.rdude.rpg.game.logic.stats.Calculatable;
import ru.rdude.rpg.game.logic.stats.Stat;

public class Lvl extends Stat {

    private enum Type {BASE, CLASS}
    public static final Type BASE = Type.BASE;
    public static final Type CLASS = Type.CLASS;

    private Exp exp;
    private Points statPoints;
    private Points skillPoints;

    public Lvl(Type type) {
        if (type == BASE) exp = new ExpBase();
        else if (type == CLASS) exp = new ExpClass();
        statPoints = new Points();
        skillPoints = new Points();
    }

    public Exp exp() { return exp; }
    public Points statPoints() { return statPoints; }
    public Points skillPoints() { return skillPoints; }

    public double expValue() { return exp.value(); }
    public double statPointsValue() { return statPoints.value(); }
    public double skillPointsValue() { return skillPoints.value(); }

    @Override
    public double increase(double value) {
        for (int i = 0; i < (int) value; i++) {
            super.increase(1);
            lvlUp();
        }
        return value;
    }

    private void lvlUp() {
        statPoints.increase(2);
        skillPoints.increase(1);
        if (value() % 3 == 0) statPoints.increase(1);
        if (value() % 10 == 0) statPoints.increase(1);
    }


    public class Points extends Stat { }


    public abstract class Exp extends Stat implements Calculatable {

        protected abstract double calculate(double lvl);
        protected double max;

        @Override
        public double calculate() {
            return calculate(Lvl.this.value());
        }

        // 'max' works like bound exceeding which lvl up
        // if bound is exceeded - calculate new bound
        @Override
        public double increase(double value) {
            this.set(value() + value);
            for (boolean isLvlUp = this.value() >= this.max; isLvlUp; isLvlUp = this.value() >= this.max) {
                Lvl.this.increase(1);
                calculate();
            }
            return value;
        }

    }

    public class ExpClass extends Exp {
        @Override
        protected double calculate(double lvl) {
            if (value() < max) return max;
            max = 170 + lvl*180 + Math.floor(lvl/2)*180 + Math.floor(lvl/3)*240;
            return max;
        }
    }

    public class ExpBase extends Exp {
        @Override
        protected double calculate(double lvl) {
            if (value() < max) return max;
            max = 100 + lvl * 150 + Math.floor(lvl/2)*160 + Math.floor(lvl/3)*185;
            return max;
        }
    }

}
