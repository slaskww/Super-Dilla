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
    public void shouldChangeSkillsAfterDroppingWeapon() {
        //Given
        Integer initialOffensiveLevel = 100;
        Integer initialDefensiveLevel = 100;
        Integer initialMentalLevel = 100;
        Player player = new Player("Player", announcer, initialOffensiveLevel, initialDefensiveLevel, initialMentalLevel);

        Weapon initialWeapon = player.getWeapon();
        Integer offensiveLevelOfInitialWeapon = initialWeapon.getOffensiveLevel();
        Integer defensiveLevelOfInitialWeapon = initialWeapon.getDefensiveLevel();

        Integer offensiveLevelOfPlayerBoostedByWeapon = player.getOffensiveLevel();
        Integer defensiveLevelOfPlayerBoostedByWeapon = player.getDefensiveLevel();

        assertThat(offensiveLevelOfPlayerBoostedByWeapon).isEqualTo(initialOffensiveLevel + offensiveLevelOfInitialWeapon);
        assertThat(defensiveLevelOfPlayerBoostedByWeapon).isEqualTo(initialDefensiveLevel + defensiveLevelOfInitialWeapon);

        player.dropWeapon();
        Integer offensiveLevelOfPlayerAfterDroppingWeapon = player.getOffensiveLevel();
        Integer defensiveLevelOfPlayerAfterDroppingWeapon = player.getDefensiveLevel();

        assertThat(offensiveLevelOfPlayerAfterDroppingWeapon).isEqualTo(offensiveLevelOfPlayerBoostedByWeapon - offensiveLevelOfInitialWeapon);
        assertThat(offensiveLevelOfPlayerAfterDroppingWeapon).isEqualTo(initialOffensiveLevel);
        assertThat(defensiveLevelOfPlayerAfterDroppingWeapon).isEqualTo(defensiveLevelOfPlayerBoostedByWeapon - defensiveLevelOfInitialWeapon);
        assertThat(defensiveLevelOfPlayerAfterDroppingWeapon).isEqualTo(initialDefensiveLevel);
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
    public void shouldChangeSkillsAfterAddingWeapon() {
        //Given
        Integer initialOffensiveLevel = 100;
        Integer initialDefensiveLevel = 100;
        Integer initialMentalLevel = 100;
        Player player = new Player("Player", announcer, initialOffensiveLevel, initialDefensiveLevel, initialMentalLevel);

        Weapon initialWeapon = player.getWeapon();
        Integer offensiveLevelOfInitialWeapon = initialWeapon.getOffensiveLevel();
        Integer defensiveLevelOfInitialWeapon = initialWeapon.getDefensiveLevel();

        Integer offensiveLevelOfPlayerBoostedByInitialWeapon = player.getOffensiveLevel();
        Integer defensiveLevelOfPlayerBoostedByInitialWeapon = player.getDefensiveLevel();

        assertThat(offensiveLevelOfPlayerBoostedByInitialWeapon).isEqualTo(initialOffensiveLevel + offensiveLevelOfInitialWeapon);
        assertThat(defensiveLevelOfPlayerBoostedByInitialWeapon).isEqualTo(initialDefensiveLevel + defensiveLevelOfInitialWeapon);

        Weapon newWeapon = WeaponFactory.ancientSword();
        Integer offensiveLevelOfNewWeapon = newWeapon.getOffensiveLevel();
        Integer defensiveLevelOfNewWeapon = newWeapon.getDefensiveLevel();

        player.addWeapon(newWeapon);
        Integer offensiveLevelOfPlayerBoostedByNewWeapon = player.getOffensiveLevel();
        Integer defensiveLevelOfPlayerBoostedByNewWeapon = player.getDefensiveLevel();

        assertThat(offensiveLevelOfPlayerBoostedByNewWeapon).isEqualTo(initialOffensiveLevel + offensiveLevelOfNewWeapon);
        assertThat(defensiveLevelOfPlayerBoostedByNewWeapon).isEqualTo(initialDefensiveLevel + defensiveLevelOfNewWeapon);
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
