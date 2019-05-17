package dialogAgent;

import drug.Drug;
import drug.DrugType;
import enemy.Enemy;
import generalplayer.PersonType;
import player.Player;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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
        System.out.println("Co robimy?\n\t(1)Uderzam na rynek po towar\t(2)Jade do szpitala\n" +
                "\t(3)Ide na piwo do pubu\t\t\t(4)Ide zjesc cos w restauracji\n" +
                "\t(5)Ide do kosciola\t\t\t\t(6)Wpadam na silownie\n" +
                "\t(7)Zmieniam miasto\t\t\t\t(8)Koncze z biznesem\n" +
                "\t(9)Sprawdzam zawartosc plecaka");
    }

    private Integer getChoice() {

        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Taki wybor to nie wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option > 0 && option < 10) {
                return option;
            }
            System.out.println("Wybierz: 1, 2, 3, 4, 5, 6, 7, 8 albo 9!");
        }

    }

    private void goTo(Integer chosenPlace) {

        switch (chosenPlace) {
            case 1:
                handleMarket();
                break;
            case 2:
                System.out.println("jestes w szpitalu");
                handleHospital();
                break;
            case 3:
                System.out.println("jestes w pubie");
                handlePub();
                break;
            case 4:
                System.out.println("jestes w restauracji");
                handleRestaurant();
                break;
            case 5:
                System.out.println("jestes w kosciele");
                handleChurch();
                break;
            case 6:
                System.out.println("jestes na silowni");
                handleGym();
                break;
            case 7:
                System.out.println("zmieniasz miasto");
                handleChangingTheCity();
                break;
            case 8:
                System.out.println("konczysz gre");
                player.kill();
                break;
            case 9: showBackpack();

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
        boolean isPlayerDealing = true;
        System.out.println(">Witaj na rynku wymiany towaru.\n");

        while(isPlayerDealing){
            showPriceList(currentPriceList);
            int transactionType = getTransactionType(); //1 = buy, 2 = sell 3 = exit 4 = show backpack

            switch(transactionType){
                case 1: handlePurchaseTransaction(player.getBalance(), currentPriceList);
                break;
                case 2: handleSaleTransaction();
                break;
                case 3: isPlayerDealing = false;
                break;
                case 4: showBackpack();
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
                           "|DIPPER EXCHANGE - IN DRUGS WE TRUST|\n" +
                           "|===================================|\n" +
                           "|   Type                Price       |\n" +
                           "|-----------------------------------|");
        for (Map.Entry<DrugType, BigDecimal> entry: priceList.entrySet()){
        System.out.format("|(%d) %-18s>> %-10.2f|\n",position++,entry.getKey().name(), entry.getValue());
        }
        System.out.println(" ===================================");

    }

    private int getTransactionType(){

        System.out.println("Wybierz: \n\t1, by dokonac kupna\t\t2, by dokonac sprzedazy\n\t3, by opuscic rynek\t\t4, by sprawdzic zawartosc plecaka");
        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Taki wybor to zaden wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option > 0 && option < 5) {
                return option;
            }
            System.out.println("Wybierz: 1, 2, 3 lub 4");
        }
    }

    private DrugType chooseDrugType(List<DrugType> drugTypeListInOrder){

        System.out.println("Wybierz cyfre odpowiadajaca towarowi na liscie");
        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Taki wybor to nie wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option > 0 && option < 6) {
              //  return DrugType.values()[option - 1];
                return drugTypeListInOrder.get(option - 1);
            }
            System.out.println("Wybierz: 1, 2, 3, 4 lub 5");
        }
    }

    private void handlePurchaseTransaction(BigDecimal walletBalance, Map<DrugType, BigDecimal> priceList){

        if (walletBalance.compareTo(BigDecimal.ZERO) == 0){
            System.out.println("Brak srodkow na zakup nowego towaru.");
            return;
        }
        List<DrugType> drugTypeList = player.getCity().getMarket().getDrugTypeListInOrder();
        DrugType chosenProductType = chooseDrugType(drugTypeList);
        BigDecimal chosenProductPrice = priceList.get(chosenProductType);
        int maxNumberOfGoodsPlayerCanAfford = walletBalance.divide(chosenProductPrice,1, BigDecimal.ROUND_HALF_DOWN).intValue();
        int numberOfPurchasedProducts = getNumberOfProductsToBuy(maxNumberOfGoodsPlayerCanAfford);

        if (numberOfPurchasedProducts == 0){return;}

        player.getSmartBackpack().addDrugToBackpack(new Drug(chosenProductType, chosenProductPrice), numberOfPurchasedProducts);
        System.out.format("Kupiles %d  sztuk produktu %s  po cenie %.2f  za sztuke.\n",numberOfPurchasedProducts, chosenProductType.name(), chosenProductPrice);

    }

    private void handleSaleTransaction(){

        if (!player.getSmartBackpack().areGoodsInBackpack()){
            System.out.println("Brak towaru na sprzedaz.");
            return;
        }
        showBackpack();
        List<DrugType> drugTypeList = player.getSmartBackpack().getDrugTypeListInOrder();
        DrugType chosenProductType = chooseDrugType(drugTypeList);
        BigDecimal chosenProductPrice = player.getCity().getMarket().getPriceList().get(chosenProductType);
        Integer volumeOfChosenProductInBackpack = player.getSmartBackpack().getDrugVolume(chosenProductType);
        int numberOfSoldProducts = getNumberOfProductsToSell(volumeOfChosenProductInBackpack);

        if (numberOfSoldProducts == 0){ return; }

        player.getSmartBackpack().removeDrugFromBackpack(new Drug(chosenProductType, chosenProductPrice), numberOfSoldProducts);
        System.out.format("Sprzedales %d  sztuk produktu %s  po cenie %.2f  za sztuke.\n",numberOfSoldProducts, chosenProductType.name(), chosenProductPrice);
    }



    private int getNumberOfProductsToBuy(int maxNumberPlayerCanAfford){

        if (maxNumberPlayerCanAfford == 0){
            System.out.println("Dostepne srodki nie pozwalaja ci na zakup tego produktu");
            return 0;
        }

        System.out.println("Dostepne srodki pozwalaja ci na zakup " + maxNumberPlayerCanAfford + " sztuk tego produktu \n" +
                "Podaj liczbe sztuk produktu, jaka chcesz kupic.");

        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Taki wybor to nie wybor. Podaj cyfre reprezentujaca dostepna liczbe towarow.");
            }
            int option = input.nextInt();

            if (option > 0 && option <= maxNumberPlayerCanAfford) {
                return option;
            }
            System.out.println("Wybierz wartosc miedzy 1 a " + maxNumberPlayerCanAfford);
        }
    }

    private int getNumberOfProductsToSell(int volumeOfChosenProductInBackpack){
        if (volumeOfChosenProductInBackpack == 0){
            System.out.println("Nie posiadasz tego produktu");
            return 0;
        }

        System.out.println("Mozesz sprzedac maksymalnie " + volumeOfChosenProductInBackpack + " sztuk tego produktu \n" +
                "Podaj liczbe sztuk produktu, jaka chcesz sprzedac.");

        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Taki wybor to nie wybor. Podaj cyfre reprezentujaca dostepna liczbe towarow.");
            }
            int option = input.nextInt();

            if (option > 0 && option <= volumeOfChosenProductInBackpack) {
                return option;
            }
            System.out.println("Wybierz wartosc miedzy 1 a " + volumeOfChosenProductInBackpack);
        }
    }


    private void showBackpack(){

        BigDecimal wallet = player.getBalance();
        Map<DrugType, Integer> goods = player.getSmartBackpack().getGoods();
        int position = 1;
        System.out.println(" _________________________________\n" +
                           "| CONTENTS OF YOUR SMART BACKPACK |\n" +
                           "|=================================|\n" +
                           "|        Type       |    Volume   |\n" +
                           "|---------------------------------|");
        for (Map.Entry<DrugType, Integer> entry: goods.entrySet()){
            System.out.format("|(%d)%-16s| %7d     |\n",position++,entry.getKey().name(), entry.getValue());
        }
        System.out.println("|---------------------------------|");
        System.out.format("|  %-17s|  %7.2f    |\n","Available funds:", wallet);
        System.out.println("|_________________________________|");




    }
}
