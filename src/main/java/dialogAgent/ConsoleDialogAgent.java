package dialogAgent;

import game.TimeInGame;
import player.Player;

import java.util.Scanner;

public class ConsoleDialogAgent implements DialogAgent {

    private Player player;
    private Scanner input;

    public ConsoleDialogAgent(Player player) {
        this.player = player;
        input = new Scanner(System.in);
    }

    @Override
    public void spectate(EventType type) {


        switch (type) {
            case GAME_STARTED:
                System.out.println("Witaj, zaczynasz karierę dobrze prosperujacego, miejmy nadzieje, przedsiebiorcy." +
                        "\nDo dyspozycji masz cztery znane ze zlej slawy miasta, w ktorych zdobedziesz cenne doswiadczenie." +
                        "\nMiej oczy otwarte i strzez sie konfliktow z lokalnymi bossami\n" +
                        "\nW każdym z miast znajdziesz miejsca, gdzie bedziesz mogl sie napic, najesc lub nieco rozerwac." +
                        "\nW kazdym z miast dziala preznie rynek wymiany towaru. Ich ceny zmienia sie z dnia na dzien," +
                        "\nwiec kupuj i sprzedawaj madrze. Szacuj podaz i blyskawicznie reaguj na popyt.\n" +
                        "\nTwoja postac posiada trzy poziomy umiejetnisci, ktore bedziesz wzbogacal w trakcie gry." +
                        "\nUmiejetnosciami tymi sa: poziom obrony, poziom ataku oraz poziom zdrowia psychicznego." +
                        "\nPoziomy obrony i ataku wykorzystasz przeciwko zlu, jakim przesiakniete sa owe miasta." +
                        "\nJesli nie chcesz stracic gruntu pod nogami, pamietaj, by utrzymywac  zdrowie psychiczne na wysokim poziomie.\n" +
                        "\nPrzygode zaczynasz w miescie GrassBay, w ktorym za sznurki pociaga nieslawny El Chipotle." +
                        "\nMasz 60 dni na to, by rozkrecic interes zycia. \nZaczynamy!\n");
                break;

            case FIRST_DAY:
                System.out.println("W miescie " + player.getCity().getName() + " nastal nowy dzien." +
                        "\nNajwyzszy czas, by co nieco zarobic! Zajrzyj na lokalny rynek towaru i poszukaj dobrych okazji." +
                        "\nSpragniony? Wpadnij do pubu na dobre piwo albo zamow stolik w lokalnej restauracji. " +
                        "\nChwila relaksu na pewno poprawi Twoje samopoczucie." +
                        "\nW swiecie pelnym przemocy wygrywaja tylko najsilniejsi. Odwiedz lokalna silownie i popracuj nad kondycja." +
                        "\nW kazdym biznesie zdarzaja sie dni lepsze i gorsze. Podlamany? Moze czas siegnac po duchowe wsparcie? " +
                        "\nZajrzyj do lokalnego kosciola i poszukaj w nim ukojenia. " +
                        "\nRozmowa z konkurencja skonczyla sie dla ciebie lekkim zlamaniem zuchwy? Daj sie uleczyc najlepszym specjalistom w miescie." +
                        "\nJesli poczujesz silna potrzebe zmiany, spakuj towar i wyjedz z miasta.\n");
                showOptions();
                Integer chosenPlace = getChoice();
                goTo(chosenPlace);
                break;

            case NEW_DAY:
                System.out.println("W miescie " + player.getCity().getName() + " nastal nowy dzien." +
                        "\nNajwyzszy czas, by co nieco zarobic!");
                showOptions();
                chosenPlace = getChoice();
                goTo(chosenPlace);
                break;

        }

    }

    private void showOptions(){
        System.out.println("Co robimy?\n\n(1)Uderzam na rynek po towar\t(2)Jade do szpitala\n" +
                "(3)Ide na piwo do pubu\t\t\t(4)Ide zjesc cos w restauracji\n" +
                "(5)Ide do kosciola\t\t\t\t(6)Wpadam na silownie\n" +
                "(7)Zmieniam miasto\t\t\t\t(8)Koncze z biznesem");
    }

    private Integer getChoice(){

        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Taki wybor to nie wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            Integer option = input.nextInt();

            if (option > 0 && option < 9) {
                return option;
            }
            System.out.println("Wybierz: 1, 2, 3, 4, 5, 6, 7 albo 8!");
        }

    }

    private void goTo(Integer chosenPlace){

        switch (chosenPlace){
            case 1:
                System.out.println("jestes na rynku");
                break;
            case 2:
                System.out.println("jestes w szpitalu");
                break;
            case 3:
                System.out.println("jestes w pubie");
                break;
            case 4:
                System.out.println("jestes w restauracji");
                break;
            case 5:
                System.out.println("jestes w kosciele");
                break;
            case 6:
                System.out.println("jestes na silowni");
                break;
            case 7:
                System.out.println("zmieniasz miasto");
                break;
            case 8:
                System.out.println("konczysz gre");
                player.kill();
                break;
        }

    }



}
