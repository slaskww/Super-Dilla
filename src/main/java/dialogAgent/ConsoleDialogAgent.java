package dialogAgent;

public class ConsoleDialogAgent implements DialogAgent{


    @Override
    public void spectate(EventType type) {


        switch(type){
            case GAME_STARTED:
                System.out.println("Witaj, zaczynasz karierę dobrze prosperujacego, miejmy nadzieje, przedsiebiorcy.");
                System.out.println("Do dyspozycji masz cztery znane ze zlej slawy miasta, w ktorych zdobedziesz cenne doswiadczenie.");
                System.out.println("Miej oczy otwarte i strzez sie konfliktow z lokalnymi bossami");
                System.out.println("W każdym z miast znajdziesz miejsca, gdzie bedziesz mogl sie napic, najesc lub nieco rozerwac.");
                System.out.println("W kazdym z miast dziala preznie rynek wymiany towaru. Ich ceny zmienia sie z dnia na dzien,");
                System.out.println("wiec kupuj i sprzedawaj madrze. Szacuj podaz i blyskawicznie reaguj na popyt.");
                System.out.println("Twoja postac posiada trzy poziomy umiejetnisci, ktore bedziesz wzbogacal w trakcie gry.");
                System.out.println("Umiejetnosciami tymi sa: poziom obrony, poziom ataku oraz poziom zdrowia psychicznego.");
                System.out.println("Poziomy obrony i ataku wykorzystasz przeciwko zlu, jakim przesiakniete sa owe miasta.");
                System.out.println("Jesli nie chcesz stracic gruntu pod nogami, pamietaj, by utrzymywac  zdrowie psychiczne na wysokim poziomie.");
                System.out.println("Przygode zaczynasz w miescie GrassBay, w ktorym za sznurki pociaga nieslawny El Chipotle.");
                System.out.println("Masz 60 dni na to, by rozkrecic interes zycia. \nZaczynaj!");

        }

    }
}
