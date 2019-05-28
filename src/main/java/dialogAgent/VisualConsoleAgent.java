package dialogAgent;

import city.Bank;
import city.City;
import city.Robberable;
import city.RobberyStatus;
import city.facility.Facility;
import city.facility.FacilityFactory;
import drug.Drug;
import drug.DrugType;
import enemy.Enemy;
import generalplayer.Person;
import generalplayer.PersonType;
import player.LinesRepository;
import player.Player;
import weapon.Weapon;
import weapon.WeaponFactory;
import world.WorldBuilder;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.*;

public class VisualConsoleAgent {

    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final int NUMBER_OF_TRANSACTIONS_ON_THE_MARKET = 4;
    public static final int NUMBER_OF_ALTERNATIVE_CHOICES_WHEN_CAUGHT_BY_POLICE = 2;
    public static final int NUMBER_OF_ALTERNATIVE_CHOICES_WHEN_CHANGE_THE_CITY = 4;
    public static final int NUMBER_OF_ALTERNATIVE_CHOICES_WHEN_IN_BANK = 3;


    static {
        input = new Scanner(System.in);
        rand = new SecureRandom();
    }

    private static Scanner input;
    private static Random rand;
    private static VisualConsoleAgent visualConsoleAgent;

    private VisualConsoleAgent() {

    }

    public static VisualConsoleAgent getVisualConsoleAgent() {
        if (visualConsoleAgent == null) {
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
                "\t(9)Sprawdzam zawartosc plecaka\t(10)Ide spac\n" +
                "\t(11)Ide do banku");
    }


    private void showStrengthLevel(Person person) {
        System.out.format("\n\tUmiejetnosci %-17s >>> Obrona: %-3d Atak: %-3d Poziom mentalny: %-3d\n", person.getName(), person.getDefensiveLevel(), person.getOffensiveLevel(), person.getMentalLevel());
    }

    private void showPriceList(Map<DrugType, BigDecimal> priceList) {
        int position = 1;

        System.out.println(ANSI_GREEN + " ===================================\n" +
                "|DIPPER EXCHANGE - IN DRUGS WE TRUST|\n" +
                "|===================================|\n" +
                "|   Type                Price       |\n" +
                "|-----------------------------------|");
        for (Map.Entry<DrugType, BigDecimal> entry : priceList.entrySet()) {
            System.out.format("|(%d) %-18s>> %-10.2f|\n", position++, entry.getKey().name(), entry.getValue());
        }
        System.out.println(" ===================================" + ANSI_RESET);

    }

    public Integer getChoice(int numberOfOptions) {

        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("# Taki wybor to nie wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option == 997) {
                return option;
            }

            if (option > 0 && option <= numberOfOptions) {
                return option;
            }
            System.out.println("# Wybierz wartosc miedzy : 1 a " + numberOfOptions);
        }

    }

    private Integer getTransactionType() {

        System.out.println("# Wybierz: \n\t1, by dokonac kupna\t\t2, by dokonac sprzedazy\n\t3, by opuscic rynek\t\t4, by sprawdzic zawartosc plecaka");
        return getChoice(NUMBER_OF_TRANSACTIONS_ON_THE_MARKET);
    }

    private DrugType chooseDrugType(List<DrugType> drugTypeListInOrder) {

        System.out.println("# Wybierz cyfre odpowiadajaca towarowi na liscie");
        int option = getChoice(DrugType.values().length);
        return drugTypeListInOrder.get(option - 1);
    }


    private int getNumberOfProductsToBuy(int maxNumberPlayerCanAfford) {

        if (maxNumberPlayerCanAfford == 0) {
            System.out.println("# Dostepne srodki nie pozwalaja ci na zakup tego produktu");
            return 0;
        }

        System.out.println("# Dostepne srodki pozwalaja ci na zakup " + maxNumberPlayerCanAfford + " sztuk tego produktu \n" +
                "# Podaj liczbe sztuk produktu, jaka chcesz kupic.");

        return getChoice(maxNumberPlayerCanAfford);
    }

    private int getNumberOfProductsToSell(int volumeOfChosenProductInBackpack) {
        if (volumeOfChosenProductInBackpack == 0) {
            System.out.println("# Nie posiadasz tego produktu");
            return 0;
        }

        System.out.println("# Mozesz sprzedac maksymalnie " + volumeOfChosenProductInBackpack + " sztuk tego produktu \n" +
                "# Podaj liczbe sztuk produktu, jaka chcesz sprzedac.");

        return getChoice(volumeOfChosenProductInBackpack);
    }


    public void showBackpack(Player player) {

        BigDecimal wallet = player.getBalance();
        Map<DrugType, Integer> goods = player.getSmartBackpack().getGoods();
        int position = 1;
        System.out.println(ANSI_PURPLE + " _________________________________\n" +
                "| CONTENTS OF YOUR SMART BACKPACK |\n" +
                "|=================================|\n" +
                "|        Type       |    Volume   |\n" +
                "|---------------------------------|");
        for (Map.Entry<DrugType, Integer> entry : goods.entrySet()) {
            System.out.format("|(%d)%-16s| %7d     |\n", position++, entry.getKey().name(), entry.getValue());
        }
        System.out.println("|---------------------------------|");
        System.out.format("|  %-17s|  %7.2f    |\n", "Available funds:", wallet);
        System.out.println("|_________________________________|" + ANSI_RESET);

    }

    public static void showActionInProgress() {

        System.out.print(ANSI_BLUE + "\n         |SERVICE IN PROGRESS|\n" + ANSI_RESET);
        System.out.print(ANSI_BLUE + " ========================================\n" + ANSI_RESET);
        System.out.print(ANSI_BLUE + "|0%                 |50%             100%|\n " + ANSI_RESET);
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(ANSI_BLUE_BACKGROUND + " " + ANSI_RESET);
        }
        System.out.print("\n\n");
    }

    private static void showFightInProgress() {

        System.out.print(ANSI_RED + "\n         |FIGHT IN PROGRESS|\n" + ANSI_RESET);
        System.out.print(ANSI_RED + " ========================================\n" + ANSI_RESET);
        System.out.print(ANSI_RED + "|0%                 |50%             100%|\n " + ANSI_RESET);
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(ANSI_RED_BACKGROUND + " " + ANSI_RESET);
        }
        System.out.print("\n\n");
    }

    public void forceEnterAction() {
        try {
            System.out.println(ANSI_BLUE + "Nacisnij ENTER aby kontynuowac..." + ANSI_RESET);
            Scanner scanner = new Scanner(System.in).useDelimiter("");
            scanner.next();
        } catch (Exception error) {
            //
        }
    }


    public boolean isViolentEnterToFacility(Player player, Robberable robberable) {
        try {
            System.out.println(ANSI_BLUE + "Nacisnij ENTER aby wejść do srodka." + ANSI_RESET);
            Scanner scanner = new Scanner(System.in).useDelimiter("");
            String input = scanner.nextLine();

            if (input.equals("rob")) {
                rob(player, robberable);
                return true;
            }


        } catch (Exception error) {
            //
        }
        return false;
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

        int option = getChoice(NUMBER_OF_ALTERNATIVE_CHOICES_WHEN_CAUGHT_BY_POLICE);

        if (option == 1 && enemy.isCorruptible() && player.getBalance().compareTo(enemy.BRIBE) >= 0) {
            System.out.println("# Masz szczescie. Policjant zgodzil sie wziac kase. Jestes stratny " + enemy.BRIBE + " ale zyjesz.");
            player.getSmartBackpack().updateWallet(BigDecimal.ZERO.subtract(enemy.BRIBE));
            return;

        } else if (option == 1 && enemy.isCorruptible()) {
            System.out.println("# Nie masz srodkow, by oplacic policje. Kwota " + enemy.BRIBE + " to dla ciebie za duzo.");
        } else if (option == 1 && !enemy.isCorruptible()) {
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
        fightMode(enemy, player);
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
            int transactionType = getTransactionType(); //1 = buy, 2 = sell 3 = exit 4 = inspect your backpack

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
                    forceEnterAction();
                    break;
            }

        }
    }

    public void handleHospital(Player player) {
        Facility hospital = FacilityFactory.hospital();
        BigDecimal costOfHospitalisation = hospital.getPrice();

        if(isViolentEnterToFacility(player, hospital)){
            return;
        }

        System.out.println("# Witaj w najlepszym szpitalu w tym miescie. Pomozemy ci stanac na nogi.");

        if (player.getBalance().compareTo(costOfHospitalisation) < 0) {
            System.out.println("# Oj, przykro nam, ale nie stac ciebie na wizyte w szpitalu. Koszt leczenia wynosi " + costOfHospitalisation.setScale(2) + ".\n" +
                    "# Przyjdz, gdy bedzie cie stac.");
            return;
        }
        showActionInProgress();
        hospital.addBenefitFromUsing(player);
        showBenefitFromUsingFacility(player, hospital);
        player.getSmartBackpack().payForFacilities(costOfHospitalisation);
        System.out.println("# Jak samopoczucie? Lepiej, prawda? Zajrzyj do nas znowu, kiedy tylko poczujesz sie slabiej.");

    }

    public void handlePub(Player player) {
        Facility pub = FacilityFactory.pub();
        BigDecimal costOfDrinks = pub.getPrice();

        if(isViolentEnterToFacility(player, pub)){
            return;
        }

        System.out.println("# Cieszymy sie, ze wpadles. Mocne ale z naszej warzelni od razu poprawi Ci humor.");

        if (player.getBalance().compareTo(costOfDrinks) < 0) {
            System.out.println("# Oj, przykro nam, ale nie stac ciebie na nasze trunki. Koszt dobrego ale to " + costOfDrinks.setScale(2) + ".\n" +
                    "# Przyjdz, gdy uzbierasz potrzebna kwote.");
            return;
        }
        showActionInProgress();
        pub.addBenefitFromUsing(player);
        showBenefitFromUsingFacility(player, pub);
        player.getSmartBackpack().payForFacilities(costOfDrinks);
        System.out.println("# Jak samopoczucie? Lepiej, prawda? Zajrzyj do nas znowu, kiedy tylko poczujesz pragnienie.");
    }

    public void handleRestaurant(Player player) {
        Facility restaurant = FacilityFactory.restaurant();
        BigDecimal costOfDinner = restaurant.getPrice();

        if(isViolentEnterToFacility(player, restaurant)){
            return;
        }

        if (player.getBalance().compareTo(costOfDinner) < 0) {
            System.out.println("# Oj, przykro nam, ale nie stac ciebie na wizyte u nas. Koszt dobrego obiadu to " + costOfDinner.setScale(2) + ".\n" +
                    "# Przyjdz, gdy uzbierasz potrzebna kwote.");
            return;
        }

        System.out.println("# Witamy szanownego goscia. Prosimy zajac miejsce przy stoliku. Nasz szef kuchni przyrzadza wlasnie pana ulubiony stek.");
        showActionInProgress();
        restaurant.addBenefitFromUsing(player);
        showBenefitFromUsingFacility(player, restaurant);
        player.getSmartBackpack().payForFacilities(costOfDinner);
        System.out.println("# Smakowalo? Nasze steki to nasza duma. Prosimy zajrzec do nas ponownie.");
    }


    public void handleChurch(Player player) {
        Facility church = FacilityFactory.church();

        if(isViolentEnterToFacility(player, church)){
            player.boostMentalLevel(-church.getMentalBenefitFromUsing());
            return;
        }

        if (player.getBalance().compareTo(church.getPrice()) < 0) {
            System.out.println("# Co laska wynosi " + church.getPrice().setScale(2) + ".\n" +
                    "# Przyjdz prosze, gdy uzbierasz potrzebna kwote.");
            return;
        }

        System.out.println("# Witam zagubiona owieczke. Zajmij prosze miejsce i poswiec chwile Bogu.");
        showActionInProgress();
        church.addBenefitFromUsing(player);
        showBenefitFromUsingFacility(player, church);
        player.getSmartBackpack().payForFacilities(church.getPrice());
        System.out.println("# Lepiej, prawda? Kazdy potrzebuje chwile duchowego wyciszenia.\n" +
                "# Odwiedz nasz kosciol, gdy tylko poczujesz sie gorzej");
    }

    public void handleGym(Player player) {
        Facility gym = FacilityFactory.gym();
        BigDecimal ticketPrice = gym.getPrice();

        if(isViolentEnterToFacility(player, gym)){
            return;
        }

        if (player.getBalance().compareTo(ticketPrice) < 0) {
            System.out.println("# Nie ma wstepu, koles. Wyrzucaj z siebie " + ticketPrice.setScale(2) + " albo pakuj w parku\n");
            return;
        }

        System.out.println("# Strzala brachu. Wolne ciezary juz na ciebie czekaja. Pakuj, szkoda czasu.");
        showActionInProgress();
        System.out.println(LinesRepository.getRandomLines() + "\n");
        gym.addBenefitFromUsing(player);
        showBenefitFromUsingFacility(player, gym);
        player.getSmartBackpack().payForFacilities(ticketPrice);
        System.out.println("# Jest pompa, nie? Klata, plecy, barki, od tego sa ciezarki!\n" +
                "# Zawin do nas jeszcze, ziomek.");

    }

    public void handleChangingTheCity(Player player) {


        City chosenCity = chooseCity(player);
        BigDecimal ticketPrice = chosenCity.getCostOfTheTicketToGetHere();

        while (player.getBalance().compareTo(ticketPrice) < 0 && !chosenCity.getName().equals(player.getCity().getName())) {
            System.out.println("# Nie posiadasz srodkow na zakup biletu.");
            chosenCity = chooseCity(player);
            ticketPrice = chosenCity.getCostOfTheTicketToGetHere();
        }

        if (chosenCity.getName().equals(player.getCity().getName())) {
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
        List<City> cities = new ArrayList<>(WorldBuilder.mapOfCities());
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

        int option = getChoice(NUMBER_OF_ALTERNATIVE_CHOICES_WHEN_CHANGE_THE_CITY);

        if (option == 4) {
            return player.getCity();
        } else {
            return cities.get(option - 1);
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

    public void handleGunShop(Player player) {
        System.out.println("# Witam pana, panie..." + player.getName() + "... dobrze pamietam? 'Siwy' wspominal o panu.\n" +
                "# Potrzebna pukawka? Mam tu troche ciekawych zabawek:\n");
        int index = 1;
        List<Weapon> weapons = WeaponFactory.getWeapons();
        for (Weapon weapon : weapons) {
            System.out.format("(%d)%s - %s, (cena: %.2f, def:%d, off:%d)\n"
                    , index++
                    , weapon.getName()
                    , weapon.getDescription()
                    , weapon.getPrice()
                    , weapon.getDefensiveLevel()
                    , weapon.getOffensiveLevel());
        }
        chooseWeaponType(weapons, player);
    }

    private void showBenefitFromUsingFacility(Player player, Facility facility) {
        System.out.println(ANSI_BLUE + "SKILLS IMPROVEMENT: " +
                "OFFENSE +" + facility.getOffensiveBenefitFromUsing() + "(" + player.getOffensiveLevel() + "), " +
                "DEFENSE +" + facility.getDefensiveBenefitFromUsing() + "(" + player.getDefensiveLevel() + "), " +
                "MENTAL +" + facility.getMentalBenefitFromUsing() + "(" + player.getMentalLevel() + ")\n" + ANSI_RESET);

    }

    private void chooseWeaponType(List<Weapon> weapons, Player player) {

        System.out.println("# Wybierz cyfre odpowiadajaca towarowi na liscie");
        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("# Taki wybor to nie wybor. Podaj cyfre reprezentujaca wybrana opcje.");
            }
            int option = input.nextInt();

            if (option == 0) {
                return;
            }

            if (option > 0 && option <= weapons.size()) {
                //  return DrugType.values()[option - 1];
                if (player.getBalance().compareTo(weapons.get(option - 1).getPrice()) >= 0) {
                    Weapon chosenWeapon = weapons.get(option - 1);
                    player.addWeapon(chosenWeapon);
                    System.out.println("# " + chosenWeapon.getName() + " to swietny wybor. Sasiedzi pozazdroszcza.");
                    player.getSmartBackpack().payForFacilities(chosenWeapon.getPrice());
                    return;

                } else {
                    System.out.println("# Poszukajmy czegos tanszego.");
                }
            }
            System.out.println("# Wybierz wartosc od 1 do " + weapons.size() + " lub 0 by zrezygnowac z kupna");
        }
    }


    public void handleBank(Player player) {

        Bank bank = player.getCity().getBank();
        
        if(isViolentEnterToFacility(player, bank)){
            return;
        }
        
        System.out.println(ANSI_YELLOW + bank.getName() + ANSI_RESET);
        System.out.println("Witamy w oddziale naszego banku. U nas moze pan zdeponowac srodki na korzystny procent.\n" +
                "W kazdej chwili moze Pan wycofac z rachunku zgromadzone srodki, zachowujac wszystkie odsetki.\n" +
                "Jaki jest pana wybor?");
        handleBankService(bank, player);

    }

    public void handleBankService(Bank bank, Player player) {

        int option = 0;

        while (option != 3) {

            System.out.format("\t(1)deponuje srodki (maksymalnie: %.2f, oproc: %.2f%%)\n\t(2)wyplacam srodki (maksymalnie: %.2f)\n\t(3)wychodze z banku\n"
                    ,player.getBalance()
                    ,(bank.getInterestRate().subtract(BigDecimal.ONE)).multiply(BigDecimal.valueOf(100))
                    ,bank.getUserBalance());

            option = getChoice(NUMBER_OF_ALTERNATIVE_CHOICES_WHEN_IN_BANK);

            switch (option) {
                case 1:
                    BigDecimal amountToDeposit = getAmountToDeposit(player);
                    bank.deposit(amountToDeposit);
                    player.getSmartBackpack().updateWallet(BigDecimal.ZERO.subtract(amountToDeposit));
                    break;
                case 2:
                    BigDecimal amountToWithdraw = getAmountToWithdraw(bank);
                    bank.withdraw(amountToWithdraw);
                    player.getSmartBackpack().updateWallet(amountToWithdraw);
                    break;

                case 3:
                    break;
            }
        }

        System.out.println("Dziekujemy za wizyte. Do widzenia");
    }

    private BigDecimal getAmountToDeposit(Player player) {

        if (player.getBalance().compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Nie posiada pan srodkow, ktore moglby Pan zdeponowac na rachunku.");
            return BigDecimal.ZERO;
        }

        System.out.println("Prosze podac kwote depozytu (maksymalnie: " + player.getBalance() + ")");
        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("# Taki wybor to nie wybor. Prosze podac wartosc liczbowa.");
            }
            int deposit = input.nextInt();

            if (deposit > 0 && BigDecimal.valueOf(deposit).compareTo(player.getBalance()) <= 0) {
                System.out.println("Kwota " + deposit + " zostala zdeponowana na Pana rachunku.");
                return BigDecimal.valueOf(deposit);
            }

            System.out.println("Kwota depozytu nie moze byc ujemna. Nie moze takze przekraczac sumy Pana srodkow.");
        }

    }

    private BigDecimal getAmountToWithdraw(Bank bank) {

        if (bank.getUserBalance().compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Nie posiada pan srodkow zdeponowanych na koncie.");
            return BigDecimal.ZERO;
        }

        System.out.println("Prosze podac kwote do wyplaty (maksymalnie: " + bank.getUserBalance() + ")");
        while (true) {
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("# Taki wybor to nie wybor. Prosze podac wartosc liczbowa.");
            }
            int amountToWithdraw = input.nextInt();

            if (amountToWithdraw > 0 && BigDecimal.valueOf(amountToWithdraw).compareTo(bank.getUserBalance()) <= 0) {
                System.out.println("Kwota " + amountToWithdraw + " zostala zdeponowana na Pana rachunku.");
                return BigDecimal.valueOf(amountToWithdraw);
            }

            System.out.println("Kwota depozytu nie moze byc ujemna. Nie moze takze przekraczac sumy Pana srodkow.");
        }

    }


    public static void bankDepositError() {
        System.out.println("Podana kwota nie moze byc ujemna.");
    }

    public static void bankWithdrawalError() {
        System.out.println("Podana kwota przekracza stan konta.");
    }


    public void rob(Player player, Robberable place) {
       RobberyStatus robberyStatus = place.rob(player);

       switch (robberyStatus){
          case FACILITY_WAS_ROBBED:
              System.out.println("Wyczysciles sejf. Masz respekt na dzielni!");
              break;
           case FACILITY_WAS_NOT_ROBBED:
               System.out.println("Pracownicy zadzwonili po gliny. Na miejscu stoczyles epicka walke, ale polegles wskutek doznanych ran.");
           break;
       }
    }

    public void showBody(){

        System.out.print(ANSI_RED + "      ___________\n"
                +"..D==|  // |   x |.....\n"
                +"..D==|___  |_|\\ x|.....\n"
                +"........//.............\n" + ANSI_RESET);
    }


}
