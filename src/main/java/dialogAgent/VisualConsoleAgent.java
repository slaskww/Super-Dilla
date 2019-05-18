package dialogAgent;

import city.City;
import city.CityFactory;
import city.facility.Facility;
import city.facility.FacilityFactory;
import drug.Drug;
import drug.DrugType;
import enemy.Enemy;
import generalplayer.Person;
import generalplayer.PersonType;
import player.Player;

import java.math.BigDecimal;
import java.util.*;

public class VisualConsoleAgent {



    static {
        input = new Scanner(System.in);
        rand = new Random();
    }

   private static Scanner input;
   private static Random rand;
   private static VisualConsoleAgent visualConsoleAgent;

    private VisualConsoleAgent() {

    }

    public static VisualConsoleAgent getVisualConsoleAgent(){
        if (visualConsoleAgent == null){
           visualConsoleAgent = new VisualConsoleAgent();
        }
        return visualConsoleAgent;
    }


    public void newDayStatement(Player player) {
        System.out.println("# W miescie " + player.getCity().getName() + " nastal nowy dzien." +
                "\n# Najwyzszy czas, by co nieco zarobic!");
    }

    public void statementAtTheEndOfTheDay() {
        System.out.println("# Sciemnia sie, wiec bezpiecznie bedzie wrocic do domu. Zaliczyles kolejny udany dzien.");
    }

    public void startGameStatement() {
        System.out.println("# Witaj, zaczynasz karierę dobrze prosperujacego, miejmy nadzieje, przedsiebiorcy." +
                "\n# Do dyspozycji masz cztery znane ze zlej slawy miasta, w ktorych zdobedziesz cenne doswiadczenie." +
                "\n# Miej oczy otwarte i strzez sie konfliktow z lokalnymi bossami\n" +
                "\n# W każdym z miast znajdziesz miejsca, gdzie bedziesz mogl sie napic, najesc lub nieco rozerwac." +
                "\n# W kazdym z miast dziala preznie rynek wymiany towaru. Ich ceny zmienia sie z dnia na dzien," +
                "\n# wiec kupuj i sprzedawaj madrze. Szacuj podaz i blyskawicznie reaguj na popyt.\n" +
                "\n# Twoja postac posiada trzy poziomy umiejetnisci, ktore bedziesz wzbogacal w trakcie gry." +
                "\n# Umiejetnosciami tymi sa: poziom obrony, poziom ataku oraz poziom zdrowia psychicznego." +
                "\n# Poziomy obrony i ataku wykorzystasz przeciwko zlu, jakim przesiakniete sa owe miasta." +
                "\n# Jesli nie chcesz stracic gruntu pod nogami, pamietaj, by utrzymywac  zdrowie psychiczne na wysokim poziomie.\n" +
                "\n# Przygode zaczynasz w miescie GrassBay, w ktorym za sznurki pociaga nieslawny El Chipotle." +
                "\n# Masz 60 dni na to, by rozkrecic interes zycia. \nZaczynamy!\n");
    }

    public void firstDayStatement(Player player) {
        System.out.println("# W miescie " + player.getCity().getName() + " nastal nowy dzien." +
                "\n# Najwyzszy czas, by co nieco zarobic! Zajrzyj na lokalny rynek towaru i poszukaj dobrych okazji." +
                "\n# Spragniony? Wpadnij do pubu na dobre piwo albo zamow stolik w lokalnej restauracji. " +
                "\n# Chwila relaksu na pewno poprawi Twoje samopoczucie." +
                "\n# W swiecie pelnym przemocy wygrywaja tylko najsilniejsi. Odwiedz lokalna silownie i popracuj nad kondycja." +
                "\n# W kazdym biznesie zdarzaja sie dni lepsze i gorsze. Podlamany? Moze czas siegnac po duchowe wsparcie? " +
                "\n# Zajrzyj do lokalnego kosciola i poszukaj w nim ukojenia. " +
                "\n# Rozmowa z konkurencja skonczyla sie dla ciebie lekkim zlamaniem zuchwy? Daj sie uleczyc najlepszym specjalistom w miescie." +
                "\n# Jesli poczujesz silna potrzebe zmiany, spakuj towar i wyjedz z miasta.\n");
    }

    public void showOptions() {
        System.out.println("# Co robimy?\n\t(1)Uderzam na rynek po towar\t(2)Jade do szpitala\n" +
                "\t(3)Ide na piwo do pubu\t\t\t(4)Ide zjesc cos w restauracji\n" +
                "\t(5)Ide do kosciola\t\t\t\t(6)Wpadam na silownie\n" +
                "\t(7)Zmieniam miasto\t\t\t\t(8)Koncze z biznesem\n" +
                "\t(9)Sprawdzam zawartosc plecaka");
    }


    private void showStrengthLevel(Person person) {
        System.out.format("\n\tUmiejetnosci %-17s >>> Obrona: %-3d Atak: %-3d Poziom mentalny: %-3d\n", person.getName(), person.getDefensiveLevel(), person.getOffensiveLevel(), person.getMentalLevel());
    }

    private void showPriceList(Map<DrugType, BigDecimal> priceList) {
        int position = 1;

        System.out.println(" ===================================\n" +
                "|DIPPER EXCHANGE - IN DRUGS WE TRUST|\n" +
                "|===================================|\n" +
                "|   Type                Price       |\n" +
                "|-----------------------------------|");
        for (Map.Entry<DrugType, BigDecimal> entry : priceList.entrySet()) {
            System.out.format("|(%d) %-18s>> %-10.2f|\n", position++, entry.getKey().name(), entry.getValue());
        }
        System.out.println(" ===================================");

    }

    public Integer getChoice() {

        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("# Taki wybor to nie wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option > 0 && option < 10) {
                return option;
            }
            System.out.println("# Wybierz: 1, 2, 3, 4, 5, 6, 7, 8 albo 9!");
        }

    }

    private int getTransactionType() {

        System.out.println("# Wybierz: \n\t1, by dokonac kupna\t\t2, by dokonac sprzedazy\n\t3, by opuscic rynek\t\t4, by sprawdzic zawartosc plecaka");
        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("# Taki wybor to zaden wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option > 0 && option < 5) {
                return option;
            }
            System.out.println("# Wybierz: 1, 2, 3 lub 4");
        }
    }

    private DrugType chooseDrugType(List<DrugType> drugTypeListInOrder) {

        System.out.println("# Wybierz cyfre odpowiadajaca towarowi na liscie");
        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("# Taki wybor to nie wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option > 0 && option < 6) {
                //  return DrugType.values()[option - 1];
                return drugTypeListInOrder.get(option - 1);
            }
            System.out.println("# Wybierz: 1, 2, 3, 4 lub 5");
        }
    }


    private int getNumberOfProductsToBuy(int maxNumberPlayerCanAfford) {

        if (maxNumberPlayerCanAfford == 0) {
            System.out.println("# Dostepne srodki nie pozwalaja ci na zakup tego produktu");
            return 0;
        }

        System.out.println("# Dostepne srodki pozwalaja ci na zakup " + maxNumberPlayerCanAfford + " sztuk tego produktu \n" +
                "# Podaj liczbe sztuk produktu, jaka chcesz kupic.");

        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Taki wybor to nie wybor. Podaj cyfre reprezentujaca dostepna liczbe towarow.");
            }
            int option = input.nextInt();

            if (option > 0 && option <= maxNumberPlayerCanAfford) {
                return option;
            }
            System.out.println("# Wybierz wartosc miedzy 1 a " + maxNumberPlayerCanAfford);
        }
    }

    private int getNumberOfProductsToSell(int volumeOfChosenProductInBackpack) {
        if (volumeOfChosenProductInBackpack == 0) {
            System.out.println("# Nie posiadasz tego produktu");
            return 0;
        }

        System.out.println("# Mozesz sprzedac maksymalnie " + volumeOfChosenProductInBackpack + " sztuk tego produktu \n" +
                "# Podaj liczbe sztuk produktu, jaka chcesz sprzedac.");

        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("# Taki wybor to nie wybor. Podaj cyfre reprezentujaca dostepna liczbe towarow.");
            }
            int option = input.nextInt();

            if (option > 0 && option <= volumeOfChosenProductInBackpack) {
                return option;
            }
            System.out.println("# Wybierz wartosc miedzy 1 a " + volumeOfChosenProductInBackpack);
        }
    }


    public void showBackpack(Player player) {

        BigDecimal wallet = player.getBalance();
        Map<DrugType, Integer> goods = player.getSmartBackpack().getGoods();
        int position = 1;
        System.out.println(" _________________________________\n" +
                "| CONTENTS OF YOUR SMART BACKPACK |\n" +
                "|=================================|\n" +
                "|        Type       |    Volume   |\n" +
                "|---------------------------------|");
        for (Map.Entry<DrugType, Integer> entry : goods.entrySet()) {
            System.out.format("|(%d)%-16s| %7d     |\n", position++, entry.getKey().name(), entry.getValue());
        }
        System.out.println("|---------------------------------|");
        System.out.format("|  %-17s|  %7.2f    |\n", "Available funds:", wallet);
        System.out.println("|_________________________________|");

    }

    private static void showActionInProgress() {

        System.out.print("\n         |SERVICE IN PROGRESS|\n");
        System.out.print(" ========================================\n");
        System.out.print("|0%                 |50%             100%|\n ");
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("=");
        }
        System.out.print("\n\n");
    }

    private static void showFightInProgress() {

        System.out.print("\n         |FIGHT IN PROGRESS|\n");
        System.out.print(" ========================================\n");
        System.out.print("|0%                 |50%             100%|\n ");
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("=");
        }
        System.out.print("\n\n");
    }

    public  void forceEnterAction() {
        try {
            System.out.println("Nacisnij ENTER aby kontynuowac...");
            Scanner scanner = new Scanner(System.in).useDelimiter("");
            scanner.next();
        } catch (Exception error) {
            //
        }
    }

    public void fight(Player player) {

        if (!player.getCity().isAnyDangerousOrdinaryEnemy()) {
            fightWithBoss(player.getCity().getBoss(), player);
            return;
        }

        int currentNumberOfEnemies = player.getCity().getEnemies().size();
        int indexOfRandomEnemy = rand.nextInt(currentNumberOfEnemies);
        Enemy randomEnemyFormTheCity = player.getCity().getEnemies().get(indexOfRandomEnemy);

        if (randomEnemyFormTheCity.getPersonType() == PersonType.NOONE) {
            player.getCity().deleteEnemy(indexOfRandomEnemy);
            return;
        }

        if (randomEnemyFormTheCity.getPersonType() == PersonType.POLICE_OFFICER) {
            fightWithPolice(randomEnemyFormTheCity, player);
            return;
        }
        fightWithDealer(randomEnemyFormTheCity, player);

        player.getCity().deleteEnemy(indexOfRandomEnemy);
    }

    private void fightWithPolice(Enemy enemy, Player player) {
        System.out.println("# Wpadles! Policja wyczula, ze dobrze idzie ci w biznesie.");
        System.out.println("# Co robisz?\n\t(1)Zaproponuj lapowke\t(2)Walcz");
        int option;

        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("# Robi sie goroco. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            option = input.nextInt();

            if (option > 0 && option < 3) {
                break;
            }
            System.out.println("# Wybierz: 1 lub 2.");
        }


        if (option == 1 && enemy.isCorruptible() && player.getBalance().compareTo(enemy.BRIBE) >= 0) {
            System.out.println("# Masz szczescie. Policjant zgodzil sie wziac kase. Jestes stratny " + enemy.BRIBE + " ale zyjesz.");
            player.getSmartBackpack().updateWallet(BigDecimal.ZERO.subtract(enemy.BRIBE));
            return;

        } else if (option == 1 && enemy.isCorruptible()) {
            System.out.println("# Nie masz srodkow, by oplacic policje. Kwota " + enemy.BRIBE + " to dla ciebie za duzo.");
        } else if (option == 1 && !enemy.isCorruptible()){
            System.out.println("# Cholera, trafil ci sie nieprzekupny glina. Musisz walczyc.");
        } else {
            System.out.println("# Wybrales walke. Coz moze lepiej byloby poswiecic te kilka stowek i zniknac w spokoju. Zatem walcz.");
        }

        fightMode(enemy, player);
    }

    private void fightWithBoss(Enemy enemy, Player player) {
        System.out.println("# By stac sie panem tego miasta musisz pokonac swojego arcywroga.");
        System.out.println("# Walczysz z bossem" + player.getCity().getBoss().getName());
        fightMode(enemy, player);
    }

    private void fightWithDealer(Enemy enemy, Player player) {
        System.out.println("# Ktos staje ci na drodze! To konkurencja, ktora mocno ostatnio wkurzyles.");
        System.out.println("# Walczysz z " + enemy.getName());
        fightMode(enemy,  player);
    }

    private void fightMode(Enemy enemy, Player player) {

        Integer accumulatedStrengthOfEnemy = enemy.getDefensiveLevel() + enemy.getOffensiveLevel() + enemy.getMentalLevel();
        Integer accumulatedStrengthOfPlayer = player.getDefensiveLevel() + player.getOffensiveLevel() + player.getMentalLevel();
        showStrengthLevel(player);
        showStrengthLevel(enemy);
        showFightInProgress();

        if (accumulatedStrengthOfEnemy.compareTo(accumulatedStrengthOfPlayer) > 0) {
            player.kill();
            System.out.println("Walczyles dzielnie, ale rany byly zbyt glebokie. Giniesz, ale miasto o tobie nie zapomni.");
        } else {
            player.reduceStrengthAfterFight(enemy);
            System.out.println("Wygrales, ale straciles sporo krwi. Przydalaby sie wizyta w szpitalu");
        }
    }



    public void handleMarket(Player player) {
        Map<DrugType, BigDecimal> currentPriceList = player.getCity().getMarket().getPriceList();
        boolean isPlayerDealing = true;
        System.out.println("# Witaj na rynku wymiany towaru.\n");

        while (isPlayerDealing) {
            showPriceList(currentPriceList);
            int transactionType = getTransactionType(); //1 = buy, 2 = sell 3 = exit 4 = show backpack

            switch (transactionType) {
                case 1:
                    handlePurchaseTransaction(player.getBalance(), currentPriceList, player);
                    break;
                case 2:
                    handleSaleTransaction(player);
                    break;
                case 3:
                    isPlayerDealing = false;
                    break;
                case 4:
                    showBackpack(player);
            }
        }
    }

    public void handleHospital(Player player) {
        Facility hospital = FacilityFactory.hospital();
        BigDecimal costOfHospitalisation = hospital.getPrice();
        System.out.println("# Witaj w najlepszym szpitalu w tym miescie. Pomozemy ci stanac na nogi.");

        if (player.getBalance().compareTo(costOfHospitalisation) < 0) {
            System.out.println("# Oj, przykro nam, ale nie stac ciebie na wizyte w szpitalu. Koszt leczenia wynosi " + costOfHospitalisation.setScale(2) + ".\n" +
                    "# Przyjdz, gdy uzbierasz potrzebna kwote.");
            return;
        }
        showActionInProgress();
        player.boostDefensiveLevel(hospital.getDefensiveBenefitFromUsing());
        player.boostOffensiveLevel(hospital.getOffensiveBenefitFromUsing());
        player.boostMentalLevel(hospital.getMentalBenefitFromUsing());
        player.getSmartBackpack().payForFacilities(costOfHospitalisation);
        System.out.println("# Jak samopoczucie? Lepiej, prawda? Zajrzyj do nas znowu, kiedy tylko poczujesz sie slabiej.");

    }

    public void handlePub(Player player) {
        Facility pub = FacilityFactory.pub();
        BigDecimal costOfDrinks = pub.getPrice();
        System.out.println("# Cieszymy sie, ze wpadles. Mocne Ale z naszej warzelni od razu poprawi Ci humor.");

        if (player.getBalance().compareTo(costOfDrinks) < 0) {
            System.out.println("# Oj, przykro nam, ale nie stac ciebie na nasze trunki. Koszt dobrego ale to " + costOfDrinks.setScale(2) + ".\n" +
                    "# Przyjdz, gdy uzbierasz potrzebna kwote.");
            return;
        }
        showActionInProgress();
        player.boostDefensiveLevel(pub.getDefensiveBenefitFromUsing());
        player.boostOffensiveLevel(pub.getOffensiveBenefitFromUsing());
        player.boostMentalLevel(pub.getMentalBenefitFromUsing());
        player.getSmartBackpack().payForFacilities(costOfDrinks);
        System.out.println("# Jak samopoczucie? Lepiej, prawda? Zajrzyj do nas znowu, kiedy tylko poczujesz pragnienie.");
    }

    public void handleRestaurant(Player player) {
        Facility restaurant = FacilityFactory.restaurant();
        BigDecimal costOfDinner = restaurant.getPrice();

        if (player.getBalance().compareTo(costOfDinner) < 0) {
            System.out.println("# Oj, przykro nam, ale nie stac ciebie na wizyte u nas. Koszt dobrego obiadu to " + costOfDinner.setScale(2) + ".\n" +
                    "# Przyjdz, gdy uzbierasz potrzebna kwote.");
            return;
        }

        System.out.println("# Witamy szanownego goscia. Prosimy zajac miejsce przy stoliku. Nasz szef kuchni przyrzadza wlasnie pana ulubiony stek.");

        player.boostDefensiveLevel(restaurant.getDefensiveBenefitFromUsing());
        player.boostOffensiveLevel(restaurant.getOffensiveBenefitFromUsing());
        player.boostMentalLevel(restaurant.getMentalBenefitFromUsing());
        player.getSmartBackpack().payForFacilities(costOfDinner);
        showActionInProgress();
        System.out.println("# Smakowalo? Nasze steki to nasza duma. Prosimy zajrzec do nas ponownie.");
    }


    public void handleChurch(Player player) {
        Facility church = FacilityFactory.church();

        if (player.getBalance().compareTo(church.getPrice()) < 0) {
            System.out.println("# Co laska wynosi " + church.getPrice().setScale(2) + ".\n" +
                    "# Przyjdz prosze, gdy uzbierasz potrzebna kwote.");
            return;
        }

        System.out.println("# Witam zagubiona owieczke. Zajmij prosze miejsce i poswiec chwile Bogu.");
        showActionInProgress();
        player.boostDefensiveLevel(church.getDefensiveBenefitFromUsing());
        player.boostOffensiveLevel(church.getOffensiveBenefitFromUsing());
        player.boostMentalLevel(church.getMentalBenefitFromUsing());
        player.getSmartBackpack().payForFacilities(church.getPrice());
        System.out.println("# Lepiej, prawda? Kazdy potrzebuje chwile duchowego wyciszenia.\n" +
                "# Odwiedz nasz kosciol, gdy tylko poczujesz sie gorzej");
    }

    public void handleGym(Player player) {
        Facility gym = FacilityFactory.gym();
        BigDecimal ticketPrice = gym.getPrice();

        if (player.getBalance().compareTo(ticketPrice) < 0) {
            System.out.println("# Nie ma wstepu, koles. Wyrzucaj z siebie " + ticketPrice.setScale(2) + " albo pakuj w parku\n");
            return;
        }

        System.out.println("# Strzala brachu. Wolne ciezary juz na ciebie czekaja. Pakuj, szkoda czasu.");
        showActionInProgress();
        player.boostDefensiveLevel(gym.getDefensiveBenefitFromUsing());
        player.boostOffensiveLevel(gym.getOffensiveBenefitFromUsing());
        player.boostMentalLevel(gym.getMentalBenefitFromUsing());
        player.getSmartBackpack().payForFacilities(ticketPrice);
        System.out.println("# Jest pompa, nie? Klata, plecy, barki, od tego sa ciezarki!\n" +
                "# Zawin do nas jeszcze, ziomek.");
    }

    public void handleChangingTheCity(Player player) {


        City chosenCity = chooseCity(player);
        BigDecimal ticketPrice = chosenCity.getCostOfTheTicketToGetHere();

        while (player.getBalance().compareTo(ticketPrice) < 0) {
            System.out.println("# Nie posiadasz srodkow na zakup biletu.");
            chosenCity = chooseCity(player);
            ticketPrice = chosenCity.getCostOfTheTicketToGetHere();
        }

        if (chosenCity.getName() == player.getCity().getName()) {
            return;
        }
        player.changeCity(chosenCity);
        player.getSmartBackpack().payForFacilities(ticketPrice);
        System.out.println("# Zyczymy milej podrozy.");
        showActionInProgress();
        System.out.println("# Witamy w " + chosenCity.getName() + ". Niech nasze miasto stanie sie Twoim nowym domem.");
    }


    private City chooseCity(Player player) {

        int position = 1;
        System.out.println("\nDostepne miasta:");
        List<City> cities = CityFactory.mapOfCities();
        City city;
        Iterator<City> iterator = cities.iterator();

        while (iterator.hasNext()) {
            city = iterator.next();
            if (city.getName().equals(player.getCity().getName())) {
                iterator.remove();
            } else
                System.out.format("\t(%d)%s - koszt biletu: %.2f\n", position++, city.getName(), city.getCostOfTheTicketToGetHere().setScale(2));
        }
        System.out.format("\t(%d)%s\n\n", position, "Zrezygnuj z wyjazdu");
        System.out.println("\n# Wybierz numer reprezentujacy miasto.");

        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("# Taki wybor to zaden wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option == 4) {
                return player.getCity();
            }

            if (option > 0 && option < 5) {
                return cities.get(option - 1);
            }
            System.out.println("# Wybierz: 1, 2, 3 lub 4.");
        }

    }



    private void handlePurchaseTransaction(BigDecimal walletBalance, Map<DrugType, BigDecimal> priceList, Player player) {

        if (walletBalance.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("# Brak srodkow na zakup nowego towaru.");
            return;
        }
        List<DrugType> drugTypeList = player.getCity().getMarket().getDrugTypeListInOrder();
        DrugType chosenProductType = chooseDrugType(drugTypeList);
        BigDecimal chosenProductPrice = priceList.get(chosenProductType);
        int maxNumberOfGoodsPlayerCanAfford = walletBalance.divide(chosenProductPrice, 1, BigDecimal.ROUND_HALF_DOWN).intValue();
        int numberOfPurchasedProducts = getNumberOfProductsToBuy(maxNumberOfGoodsPlayerCanAfford);

        if (numberOfPurchasedProducts == 0) {
            return;
        }

        player.getSmartBackpack().addDrugToBackpack(new Drug(chosenProductType, chosenProductPrice), numberOfPurchasedProducts);
        System.out.format("# Kupiles %d  sztuk produktu %s  po cenie %.2f  za sztuke.\n", numberOfPurchasedProducts, chosenProductType.name(), chosenProductPrice);

    }

    private void handleSaleTransaction(Player player) {

        if (!player.getSmartBackpack().areGoodsInBackpack()) {
            System.out.println("# Brak towaru na sprzedaz.");
            return;
        }
        showBackpack(player);
        List<DrugType> drugTypeList = player.getSmartBackpack().getDrugTypeListInOrder();
        DrugType chosenProductType = chooseDrugType(drugTypeList);
        BigDecimal chosenProductPrice = player.getCity().getMarket().getPriceList().get(chosenProductType);
        Integer volumeOfChosenProductInBackpack = player.getSmartBackpack().getDrugVolume(chosenProductType);
        int numberOfSoldProducts = getNumberOfProductsToSell(volumeOfChosenProductInBackpack);

        if (numberOfSoldProducts == 0) {
            return;
        }

        player.getSmartBackpack().removeDrugFromBackpack(new Drug(chosenProductType, chosenProductPrice), numberOfSoldProducts);
        System.out.format("# Sprzedales %d  sztuk produktu %s  po cenie %.2f  za sztuke.\n", numberOfSoldProducts, chosenProductType.name(), chosenProductPrice);
    }


}
