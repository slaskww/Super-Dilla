package player;

import city.City;
import city.CityFactory;
import enemy.Enemy;
import enemy.EnemyFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Announcer;
import weapon.Weapon;
import weapon.WeaponFactory;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayerTest {

    Announcer announcer = new Announcer();

    @Test
    public void shouldBoostOffensiveLevel() {
        //Given
        Player player = new Player("Player", announcer);
        Integer currentOffensiveLevel = player.getOffensiveLevel();
        Integer addedOffensiveLevel = 30;

        //When
        player.boostOffensiveLevel(addedOffensiveLevel);

        //Then
        assertThat(player.getOffensiveLevel()).isEqualTo(currentOffensiveLevel + addedOffensiveLevel);
     }

    @Test
    public void shouldBoostDefensiveLevel() {

        //Given
        Player player = new Player("Player", announcer);
        Integer currentDefensiveLevel = player.getDefensiveLevel();
        Integer addedDefensiveLevel = 30;

        //When
        player.boostDefensiveLevel(addedDefensiveLevel);

        //Then
        assertThat(player.getDefensiveLevel()).isEqualTo(currentDefensiveLevel + addedDefensiveLevel);
    }

    @Test
    public void shouldBoostMentalLevel() {
        //Given
        Player player = new Player("Player", announcer);
        Integer currentMentalLevel = player.getMentalLevel();
        Integer addedMentalLevel = 30;

        //When
        player.boostMentalLevel(addedMentalLevel);

        //Then
        assertThat(player.getMentalLevel()).isEqualTo(currentMentalLevel + addedMentalLevel);
    }

    @Test
    public void shouldDropWeapon() {
        //Given
        Player player = new Player("Player", announcer);
        boolean isWeaponBeforeDropping = player.isWeapon();

        //When
        player.dropWeapon();
        boolean isWeaponAfterDropping = player.isWeapon();

        //Then
        assertThat(isWeaponBeforeDropping).isTrue();
        assertThat(isWeaponAfterDropping).isFalse();
    }

    @Test
    public void shouldAddWeapon() {
        //Given
        Player player = new Player("Player", announcer);
        Weapon oldWeapon = player.getWeapon();
        player.dropWeapon();
        Weapon newWeapon = WeaponFactory.machineGun();
        //When
        player.addWeapon(newWeapon);
        Weapon weaponPreviouslyAdded = player.getWeapon();

        //Then
        assertThat(newWeapon).isNotEqualTo(oldWeapon);
        assertThat(newWeapon).isEqualTo(weaponPreviouslyAdded);
    }

    @Test
    public void shouldChangeCity() {
        //Given
        Player player = new Player("Player", announcer);
        City initialCity = player.getCity();


        City newCity = CityFactory.whiteMountain();

        //When
        player.changeCity(newCity);
        City cityOfPlayerAfterChange = player.getCity();
        //Then
        assertThat(initialCity).isNotEqualTo(newCity);
        assertThat(newCity).isEqualTo(cityOfPlayerAfterChange);
    }




    @Test
    public void reduceStrengthAfterFight() {
        //Given
        Player player = new Player("Player", announcer);

        Integer initialDefensiveLevelOfPlayer = player.getDefensiveLevel();
        Integer initialOffensiveLevelOfPlayer = player.getOffensiveLevel();
        Integer initialMentalLevelOfPlayer = player.getMentalLevel();

        Enemy enemy = EnemyFactory.policeOfficer();
        Integer initialDefensiveLevelOfEnemy = enemy.getDefensiveLevel();
        Integer initialOffensiveLevelOfEnemy = enemy.getOffensiveLevel();
        Integer initialMentalLevelOfEnemy = enemy.getMentalLevel();

        //When
        player.reduceStrengthAfterFight(enemy);
        Integer defensiveLevelOfPlayerAfterReduction = player.getDefensiveLevel();
        Integer offensiveLevelOfPlayerAfterReduction = player.getOffensiveLevel();
        Integer mentalLevelOfPlayerAfterReduction = player.getMentalLevel();

        //Then
        assertThat(defensiveLevelOfPlayerAfterReduction).isEqualTo(initialDefensiveLevelOfPlayer - initialDefensiveLevelOfEnemy);
        assertThat(offensiveLevelOfPlayerAfterReduction).isEqualTo(initialOffensiveLevelOfPlayer - initialOffensiveLevelOfEnemy);
        assertThat(mentalLevelOfPlayerAfterReduction).isEqualTo(initialMentalLevelOfPlayer - initialMentalLevelOfEnemy);
    }

}
