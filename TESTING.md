# Unit Tests

## Milestone M2

CreatePathTest
+ Purpose: Confirms that the path can be created on the map.
+ Description: Instrumentation test which creates a gridmap and confirms initial status of map and path. Checks that empty tiles are empty and of the EmptyTile class. Checks that Path is not empty and of the Path class.

HealthVariesTest
+ Purpose: Confirms that monument health varies with difficulty levels.
+ Description: Obtains player health at different difficulty levels. Compares them, asserting that they are not equal. 

MoneyVariesTest
+ Purpose: Confirms that money varies with difficulty levels.
+ Description: Obtains player money at different difficulty levels. Compares them, asserting that they are not equal. 

## Milestone M3

TowerOnPathTest
+ Purpose: Confirms that towers are not allowed to be placed on the path.
+ Description: Instrumentation test which creates a grid map and then tries to place a tower on the path. The grid map should throw a IllegalArgumentException when attempted.

TowerPricesVaryTest
+ Purpose: Confirms that the prices of towers vary with the difficulty level.
+ Description: Creates three different towers on Easy and Hard mode and asserts that the easy version of a tower and the hard version of a tower have different prices. 

InsufficientMoneyPreventsPurchaseTest
+ Purpose: Confirms that a user cannot buy towers without having enough money. 
+ Description: There is only enough money for one RootKit, but there is an attempt to purchase a RootKit twice. The first and only purchase is popped from the towerStack. The test checks that the towerStack is empty as the second attempt at a purchase is invalid due to insufficient funds.

PlaceTowerAfterPurchaseTest
+ Purpose: Confirms that users can place towers onto a map (specifically the internal representation)
+ Description: Instrumentation test that adds a virus tower to the tower stack and attempts to place it onto the map. It then asserts that the map tile is an instance of the virus tower.

PurchaseLimitedToOneTowerTest
+ Purpose: Confirms that one purchase corresponds to one tower joining the towerStack to be placed. 
+ Description: A virus tower is added to the towerStack upon purchase. That tower is popped from the towerStack. The test confirms that the stack is empty, demonstrating that only one virus tower was purchased. 

## Milestone M4

PathIsContiguousAndUnidirectionalTest
+ Purpose: Confirms that an enemy only travels upon contiguous tiles in one direction; otherwise, enemies can move backwards and forwards as well as jump around, violating logical enemy movement
+ Description: Instrumentation test that checks that consecutive tiles in the path sequence list are direct neighbors of each other

PathPositionsHaveOnlyOneValidNeighborTest
+ Purpose: Confirms that the enemy has only one option for every next step from the path position; otherwise, enemies cannot move in a logical and linear fashion on the map, violating logical enemy movement
+ Description: Instrumentation test that checks that only one of the neighbors for a path is valid as a next step 

EnemyOnlyTravelsOnPathTest
+ Purpose: Confirms that the enemy can only travel on the path
+ Description: Instrumentation test that checks that the corresponding map tiles for the enemy's path sequence are instances of Path 

TimerScheduleTest
+ Purpose: Confirms that timer tasks can be scheduled and activate on time
+ Description: Instrumentation test using a timed money decrease task to confirm that scheduled tasks are succesfully performed by timer

EnemyTravelMonumentTest
+ Purpose: Confirms that an enemy traversing path reaches monument
+ Description: Instrumentation test starting an enemy at the start of the path and then keeping cycling until it reaches the end of the path sequence. Checks if it has reached the monument and can be removed successfully

SpawnedEnemiesDifferTest
+ Purpose: Confirms that different types of enemies are spawned when combat starts
+ Description: Instrumentation test initializing the EnemyEngine to generate the currentEnemies that are to be spawned. Checks if each type of enemy is represented in the list

EnemyDecreasesTest
+ Purpose: Confirms that the enemy leaves the map once it reaches and passes the monument
+ Description: Instrumentation test that moves an enemy to the position of the monument in the internal representation of the map and checks that one less enemy is in currentEnemies

HealthDecreasesTest
+ Purpose: Confirms that the health decreases when the enemy reaches the monumuent
+ Description: Instrumentation test that checks that the health of the monument is less after an enemy reaches the monument and damages it.

## Milestone M5

EnemyInRangeTest
+ Purpose: Confirms that towers have a concept of proximity and only attack enemies within their range
+ Description: Instrumentation test using the enemy target selection function to see if it selects an enemy outside a tower's range. Checks if a tower can see an enemy that is too far.

EnemyHealthVisibleTest
+ Purpose: Confirms that the opacity of enemies that have been damaged is lower
+ Description: Instrumentation test dealing damage to an enemy and checking its opacity at different stages to make sure it's calculated correctly for visible combat.

EnemyHealthVaryTest
+ Purpose: Confirms that differnt enemies have varying starting health 
+ Description: Obtains starting health of different enemies. Compares them, asserting that they are not equal. 

TowerRangeVaryTest
+ Purpose: Confirms that the different towers have differnt proximity where they can attack
+ Description: Obtains the range of attack of different towers. Compares them, asserting that they are not equal. 

EnemyDiesTest
+ Purpose: Confirms that enemies can die by being removed from the internal representation of current enemies
+ Description: Adds and removes different lists of current enemies, including one randomly generated by EnemyEngine, and ensures that the enemy counts differ and are actually removed

TowersAttackDifferentlyTest
+ Purpose: Confirms that the towers are meaninfuly distinct with regards to the enemies they choose to attack
+ Description: Places towers on a map and determines the targets of two different towers, asserting that they are not equal

EnemyDamageDiffersTest
+ Purpose: Confirms that enemies are meanigfully distinct with regards to the damage they deliver
+ Description: Obtains the damage each enemy would deliver to the player. Compares them, asserting that they are not equal

EnemyCannotHaveSubZeroHealthTest
+ Purpose: Confirms that enemies cannot have subzero health as they must die when their health reaches 0
+ Description: Instrumentation test that attempts to set an enemy health to below zero, but throws an exception

## Milestone M6

TowerPowerUpgradeTest
+ Purpose: Confirms that upgrade to the attack power of a tower can only be done once 
+ Description: Obtains starting attack power of a tower, upgrade the attack power the first time then assert that they are not equal. 
Obtains attack power of the tower after the first upgrade, upgrade the attack power the second time then assert that they are equal.

TowerRangeUpgradeTest
+ Purpose: Confirms that upgrade to the attack range of a tower can only be done once 
+ Description: Obtains starting attack range of a tower, upgrade the attack range the first time then assert that they are not equal. 
Obtains attack range of the tower after the first upgrade, upgrade the attack range the second time then assert that they are equal.

RangeUpgradeModifiesTowersTest
+ Purpose: Confirms that an upgrade (the range upgrade in this case) modifies towers
+ Description: Buys and applies the range upgrade for each type of tower. Compares the ranges for each tower before and after the upgrade, asserting that they are not equal. 

PowerUpgradeModifiesTowersTest
+ Purpose: Confirms that an upgrade (the power upgrade in this case) modifies towers
+ Description: Buys and applies the power upgrade for each type of tower. Compares the powers for each tower before and after the upgrade, asserting that they are not equal. 

FinalBossIsDifficultTest
+ Purpose: Confirms that the final boss is more difficult to defeat than the other enemies
+ Description: Spawns each type of enemy and the final boss. Compares the damanges, asserting that the value for the final boss is higher for each. 

KillStatisticIsCorrectTest
+ Purpose: Confirms that the kill statistic, one of the final 3 statistics displayed at the end, is correct
+ Description: Spawns and removes enemies, asserting that the kill statistic value is the same as the number of enemies removed.

TowerUpgradeSpriteTest
+ Purpose: Confirms that buying an upgrade changes the sprite for that tower
+ Description: Buys a tower and then upgrades it twice. Checks if the sprite before and after buying each upgrade are different.

NoDuplicateUpgradeTest
+ Purpose: Confirm that an upgrade can only be bought once, and money is decremented accordingly
+ Description: Buys an upgrade and asserts that money was spent to buy it. Tries to buy the same upgrade again without applying it, asserting no money change.


