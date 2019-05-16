package dialogAgent;

import drug.DrugType;
import enemy.Enemy;
import generalplayer.PersonType;
import player.Player;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class ConsoleDialogAgent implements DialogAgent {

    private Player player;
    private Scanner input;
    private Random rand;

    public ConsoleDialogAgent(Player player) {
        this.player = player;
        input = new Scanner(System.in);
        rand = new Random();
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
                player.getCity().getMarket().changePrices();
                showOptions();
                chosenPlace = getChoice();
                goTo(chosenPlace);
                break;

            case FIGHT:
                fight();

        }

    }

    private void showOptions() {
        System.out.println("Co robimy?\n\n(1)Uderzam na rynek po towar\t(2)Jade do szpitala\n" +
                "(3)Ide na piwo do pubu\t\t\t(4)Ide zjesc cos w restauracji\n" +
                "(5)Ide do kosciola\t\t\t\t(6)Wpadam na silownie\n" +
                "(7)Zmieniam miasto\t\t\t\t(8)Koncze z biznesem");
    }

    private Integer getChoice() {

        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Taki wybor to nie wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option > 0 && option < 9) {
                return option;
            }
            System.out.println("Wybierz: 1, 2, 3, 4, 5, 6, 7 albo 8!");
        }

    }

    private void goTo(Integer chosenPlace) {

        switch (chosenPlace) {
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

    private void fight() {

        if (!player.getCity().isAnyDangerousOrdinaryEnemy()) {
            System.out.println("Walczysz z bossem" + player.getCity().getBoss().getName());
            return;
        }

        int currentNumberOfEnemies = player.getCity().getEnemies().size();
        int indexRandomEnemy = rand.nextInt(currentNumberOfEnemies);
        Enemy randomEnemyFormTheCity = player.getCity().getEnemies().get(indexRandomEnemy);

        if (randomEnemyFormTheCity.getPersonType() == PersonType.NOONE) {
            System.out.println("Udalo ci sie uniknac konfrontacji z twoimi wrogami. Jutro mozesz nie miec tyle szczescia.");
            player.getCity().deleteEnemy(indexRandomEnemy);
            return;
        }
        System.out.println("Walczysz z " + randomEnemyFormTheCity.getName());
        player.getCity().deleteEnemy(indexRandomEnemy);
    }

    private void handleMarket(){
        Map<DrugType, BigDecimal> currentPriceList = player.getCity().getMarket().getPriceList();

        while(true){
            showPriceList(currentPriceList);
            int transactionType = getTransactionType(); //1 = buy, 2 = sell

            switch(transactionType){
                case 1: handlePurchaseProcedure(player.getBalance(), currentPriceList);
                break;
                case 2: handleSaleProcedure();
                break;
            }
        }


    }
    private void handleHospital(){}
    private void handlePub(){}
    private void handleRestaurant(){}
    private void handleChurch(){}
    private void handleGym(){}
    private void handleChangingTheCity(){}


    private void showPriceList(Map<DrugType, BigDecimal> priceList){
        int position = 1;

        System.out.println(" ===================================\n" +
                           "|DOPER EXCHANGE - CURRENT PRICE LIST|\n" +
                           "|===================================|");
        for (Map.Entry<DrugType, BigDecimal> entry: priceList.entrySet()){
        System.out.format("|(%d) %-18s>> %-10.2f|\n",position++,entry.getKey().name(), entry.getValue());
        }
        System.out.println(" ===================================");

    }

    private int getTransactionType(){

        System.out.println("Wybierz: \n\t1, by dokonac kupna\t2, by dokonac sprzedazy");
        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Taki wybor to nie wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option > 0 && option < 3) {
                return option;
            }
            System.out.println("Wybierz: 1 lub 2");
        }
    }

    private DrugType chooseDrugType(){

        System.out.println("Wybierz cyfre odpowiadajaca towarowi na liscie");
        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Taki wybor to nie wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option > 0 && option < 6) {
                return DrugType.values()[option - 1];
            }
            System.out.println("Wybierz: 1, 2, 3, 4 lub 5");
        }
    }

    private void handlePurchaseProcedure(BigDecimal walletBalance, Map<DrugType, BigDecimal> priceList){

        if (walletBalance.compareTo(BigDecimal.ZERO) == 0){
            System.out.println("Brak srodkow na zakup nowego towaru.");
            return;
        }
        DrugType chosenProductType = chooseDrugType();
        BigDecimal chosenProductPrice = priceList.get(chosenProductType);





    }
}
