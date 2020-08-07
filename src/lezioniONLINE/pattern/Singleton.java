package pattern;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Singleton {
    /**
     * Classe da instaziare solo 1 volta
     */
    public static class Screen { }

    /**
     * Classe da instaziare solo 1 volta
     */
    public static class Display{

        @NotNull
        private Screen s;

        @Nullable
        private static Display instance = null;

        private Display(){
            this.s = new Screen();
            // iniazializza
        }

        /**
         * Funzione per ritornare l'instanza dell'oggetto
         * @return The instance of object Singleton (the only instance of Singleton)
         */
        @NotNull
        public static Display getInstance(){
            if (instance == null)
                instance = new Display();
            return instance;
        }

    }

    public static void main(String[] args) {
        Display d1 = Display.getInstance();
        Display d2 = Display.getInstance();

    }
}
