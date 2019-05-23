package player;

import drug.DrugType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LinesRepository {

    private static List<String> listOfLines;
    private static Random  random;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_RESET = "\u001B[0m";


    static{
        random = new Random();
        listOfLines = new ArrayList<>(Arrays.asList(ANSI_RED + "Jak tam biznes? Slyszalem, ze idzie popyt na " + DrugType.COCAINE.name() + ". Lepiej zrobic male zapasy." + ANSI_RESET,
                ANSI_RED + "Siwy twierdzi, ze grube sledzie beda puszczac na rynek swoje zapasy " + DrugType.ESCTASY + ". Jesli to prawda, to bedzie zwala na cenie."+ ANSI_RESET,
                ANSI_RED + "Ty, kurna, wez sie odlep od mojej Mariolki, bo ci kulturalnie mowie, ze ci morde zwalcuje"+ ANSI_RESET,
                ANSI_RED + "Stary, bez koksu to teraz nie ma opcji, co nie? Nic bez niej nie rosnie."+ ANSI_RESET,
                ANSI_RED + "Mam psa w rodzinie. Gada, ze bedzie lapanka na dzielni. Lepiej uwazac."+ ANSI_RESET,
                ANSI_RED + "Powinienes ogarnac jakas pukawke dla swojego zabezpieczenia. Rekomenduje pana Staszka. To uczciwy recydywista. Wal w 99 i powolaj sie na Grubego."+ ANSI_RESET,
                ANSI_RED + "Wpienia mnie ten bank, normalnie. Wzielem kase na kredyt i tera dzwonia, ze mam im jakis hajs zwracac. Niech sie gonia, dali to bralem."+ ANSI_RESET,
                ANSI_RED + "Uczciwie to juz sie nie da zyc w tym miescie. Wszedzie zlodziejstwo i szachrajstwo."+ ANSI_RESET,
                ANSI_RED + "Stary, jak zyc? Kasy nie ma a palic sie chce. Daj jakies cygarety."+ ANSI_RESET,
                ANSI_RED + "Typie, nie wiem co jest, ale " + DrugType.HEROIN + " podchodzi mi coraz slabiej. Chyba zmienie dostawce"+ ANSI_RESET,
                ANSI_RED + "Zakochalem sie w Marysce. Martwi mie jednak fakt, ze ten zwiazek kosztuje mnie coraz wiecej."+ ANSI_RESET,
                ANSI_RED + "Slyszales moze o dobrym skladziku z bronia? Podobno stukasz 99 i jestes w albanskim raju."+ ANSI_RESET,
                ANSI_RED + "Jesli cena " + DrugType.HEROIN  + " szybko nie pojdzie w gore, to jestem utopiony."+ ANSI_RESET,
                ANSI_RED + "Szykuje sie ustawka na Dzikich Polach. Bedzie grill, maczety i jakies bejsbole."+ ANSI_RESET,
                ANSI_RED + "Ktos trzesie cenami na rynku. Sciera gadal, ze to ludzie w lakerkach ogarneli system, i tera beda trzepac kase na bialym towarze"+ ANSI_RESET,
                ANSI_RED + "Chyba wejde te bitkojny. Cena za sztuke to 15 patykow. Chyba promo, bo gadaja, ze za chwile bedzie lazil po milionie"+ ANSI_RESET,
                ANSI_RED + "Mam pewne info, ze na rynku brakuje " +  DrugType.AMPHETAMINE + ". Wchodze w ten towar all in i powiadam ci, bedzie mocny ruch w gore"+ ANSI_RESET,
                ANSI_RED + "997, ten numer to klopoty. Gdy wydazy sie incydent, to pojawia sie konfident..."+ ANSI_RESET,
                ANSI_RED + "Skoncz juz ganiac z ta zdzawa pala. Zakup jakas szacowna bron i pokaz sie miastu. Stukaj 99 i wkraczaj po sprzet."+ ANSI_RESET
                ));
    }

    private LinesRepository() {
    }

    public static String getRandomLines(){
        int randomIndex = random.nextInt(listOfLines.size());
        String dialogueToReturn = listOfLines.get(randomIndex);
        listOfLines.remove(randomIndex);
        return dialogueToReturn;
    }
}




