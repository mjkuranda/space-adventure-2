# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.29.0] - 2023-08-21
### Changed
- [@mjkuranda](https://github.com/mjkuranda): Background music looped.

## [0.28.1] - 2023-08-21
### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Asteroids do not spawn next to each other.

## [0.28.0] - 2023-08-21
### Added
- [@mjkuranda](https://github.com/mjkuranda): Two engine fire animations.

## [0.27.0] - 2023-08-19
### Added
- [@mjkuranda](https://github.com/mjkuranda): Flowing the mesh in `RetroRenderer`.

## [0.26.0] - 2023-08-19
### Added
- [@mjkuranda](https://github.com/mjkuranda): Explosions in Game over state.
- [@mjkuranda](https://github.com/mjkuranda): Explosions in Main menu state.

### Changed
- [@mjkuranda](https://github.com/mjkuranda): Destroying an asteroid gives 2-4 new missiles.

### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Game animations while switching any renderer.
- [@mjkuranda](https://github.com/mjkuranda): Resetting input during entering to the game state.

## [0.25.0] - 2023-08-18
### Added
- [@mjkuranda](https://github.com/mjkuranda): Credits state.

## [0.24.0] - 2023-08-18
### Added
- [@mjkuranda](https://github.com/mjkuranda): Game music.

## [0.23.0] - 2023-08-17
### Added
- [@mjkuranda](https://github.com/mjkuranda): Game sounds.

## [0.22.0] - 2023-08-14
### Added
- [@mjkuranda](https://github.com/mjkuranda): Fading out and in text label in main menu.
- [@mjkuranda](https://github.com/mjkuranda): Fading out and in sign next to input string menu option.
- [@mjkuranda](https://github.com/mjkuranda): Space screens animation from `Space Adventure 2019`.

### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Loading resource names in `LoadingState`.

## [0.21.0] - 2023-08-12
### Added
- [@mjkuranda](https://github.com/mjkuranda): `GameBar` component.
- [@mjkuranda](https://github.com/mjkuranda): `LongGameBar` component.
- [@mjkuranda](https://github.com/mjkuranda): Time and durability as game bars.
- [@mjkuranda](https://github.com/mjkuranda): Score, nickname and missile count as text on the screen.
- [@mjkuranda](https://github.com/mjkuranda): Empty input for your name gives `Noname` player name.
- [@mjkuranda](https://github.com/mjkuranda): Info rendering - press `H` to toggle its.

## [0.20.0] - 2023-08-11
### Added
- [@mjkuranda](https://github.com/mjkuranda): Game time up to 5 minutes.
- [@mjkuranda](https://github.com/mjkuranda): Spawning a new entity probability depending on left game time.
- [@mjkuranda](https://github.com/mjkuranda): Missile count.
- [@mjkuranda](https://github.com/mjkuranda): Gathering 2 or 3 missiles by destroying asteroids.

## [0.19.0] - 2023-08-09
### Added
- [@mjkuranda](https://github.com/mjkuranda): Spacecraft vibration after collision.

### Changed
- [@mjkuranda](https://github.com/mjkuranda): `PlayerStatistics` to `PlayerData`.
- [@mjkuranda](https://github.com/mjkuranda): `LaserMissile` from `50` to `75`.

## [0.18.1] - 2023-08-09
### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Resetting player statistics.

## [0.18.0] - 2023-08-05
### Added
- [@mjkuranda](https://github.com/mjkuranda): HighScore state.
- [@mjkuranda](https://github.com/mjkuranda): HighScoreRecord and its DTO.
- [@mjkuranda](https://github.com/mjkuranda): HighScoreHandler to handle high score.
- [@mjkuranda](https://github.com/mjkuranda): Saving and writing to the file of high scores.

### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Resetting the other menu states after entering to another one.

## [0.17.0] - 2023-08-03
### Added
- [@mjkuranda](https://github.com/mjkuranda): Loading state.

## [0.16.1] - 2023-08-03
### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Each menu state is resetting after entering to another one state.

## [0.16.0] - 2023-08-03
### Added
- [@mjkuranda](https://github.com/mjkuranda): `NewGameMenuState` to init your name.

## [0.15.2] - 2023-08-01
### Added
- [@mjkuranda](https://github.com/mjkuranda): Maximal entity count in map up to 32 entities.

### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Rendering entities in `RetroRenderer`.

## [0.15.1] - 2023-07-29
### Added
- [@mjkuranda](https://github.com/mjkuranda): Space char in `InputMenuOption`.

### Changed
- [@mjkuranda](https://github.com/mjkuranda): Spawning entity coordinates.

### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Player collision with entities.

## [0.15.0] - 2023-07-28
### Added
- [@mjkuranda](https://github.com/mjkuranda): MenuOptionValue, StringMenuOptionValue to store form values.
- [@mjkuranda](https://github.com/mjkuranda): InputMenuOptionEvent to handling inputting keys.
- [@mjkuranda](https://github.com/mjkuranda): InputMenuOption as formant to inputting string.

### Changed
- [@mjkuranda](https://github.com/mjkuranda): GameOverState menu state.

### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Height of `>` and `<` characters in MenuOption.
- [@mjkuranda](https://github.com/mjkuranda): Entities do not give points after destroying them by collision with spaceship.

## [0.14.0] - 2023-07-27
### Added
- [@mjkuranda](https://github.com/mjkuranda): GameAnimation as a game resource.
- [@mjkuranda](https://github.com/mjkuranda): Spawning an explosion animation after destroying asteroid.
- [@mjkuranda](https://github.com/mjkuranda): Damaging player spaceship.

### Changed
- [@mjkuranda](https://github.com/mjkuranda): RetroRenderer refactored rendering entities.
- [@mjkuranda](https://github.com/mjkuranda): Missiles damage other entities instead of immediately destroy them.

## [0.13.0] - 2023-07-26
### Added
- [@mjkuranda](https://github.com/mjkuranda): Each entity has an image reference.
- [@mjkuranda](https://github.com/mjkuranda): GameResource as an interface.
- [@mjkuranda](https://github.com/mjkuranda): GameImage containing all images.
- [@mjkuranda](https://github.com/mjkuranda): GameFont containing all fonts.
- [@mjkuranda](https://github.com/mjkuranda): Centering of MenuOption text.
- [@mjkuranda](https://github.com/mjkuranda): Title, version and creator text in main menu state.

### Changed
- [@mjkuranda](https://github.com/mjkuranda): Any GameResource directly returns its resource.

### Removed
- [@mjkuranda](https://github.com/mjkuranda): ImageFactory, GameImages classes.

## [0.12.0] - 2023-07-26
### Added
- [@mjkuranda](https://github.com/mjkuranda): Menu, MenuOption and MenuOptionEvent system.
- [@mjkuranda](https://github.com/mjkuranda): Main menu as first game state.
- [@mjkuranda](https://github.com/mjkuranda): Simple start and exit options.

## [0.11.0] - 2023-07-11
### Added
- [@mjkuranda](https://github.com/mjkuranda): Image loading.
- [@mjkuranda](https://github.com/mjkuranda): Rendering image for player, asteroid and background.
- [@mjkuranda](https://github.com/mjkuranda): Distance to player stats.

## [0.10.0] - 2023-07-11
### Added
- [@mjkuranda](https://github.com/mjkuranda): GameOver state.

### Changed
- [@mjkuranda](https://github.com/mjkuranda): Constructor design.

### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Asteroids spawn inside the map only.

## [0.9.0] - 2023-07-10
### Added
- [@mjkuranda](https://github.com/mjkuranda): Missiles rendering.
- [@mjkuranda](https://github.com/mjkuranda): PlayerStatistics, which allows to collect the points!

### Changed
- [@mjkuranda](https://github.com/mjkuranda): ArcadeRenderer centered.

### Fixed
- [@mjkuranda](https://github.com/mjkuranda): All SpaceEntities rendering size.

## [0.8.0] - 2023-07-01
### Added
- [@mjkuranda](https://github.com/mjkuranda): Asteroids rendering.

### Changed
- [@mjkuranda](https://github.com/mjkuranda): GameData size changed to 16x16 board size.

## [0.7.0] - 2023-06-28
### Added
- [@mjkuranda](https://github.com/mjkuranda): RetroRenderer as a pseudo 3D renderer.
- [@mjkuranda](https://github.com/mjkuranda): Dynamically changing renderer by 1 or 2 key on the keyboard.

### Changed
- [@mjkuranda](https://github.com/mjkuranda): GameData map size to 16x32 tiles.
- [@mjkuranda](https://github.com/mjkuranda): Position of ArcadeRender to center position.

## [0.6.0] - 2023-06-26
### Added
- [@mjkuranda](https://github.com/mjkuranda): EntityShapes for all entities.

### Changed
- [@mjkuranda](https://github.com/mjkuranda): Remove magic numbers 32.
- [@mjkuranda](https://github.com/mjkuranda): Spaceship y axis to 1.
- [@mjkuranda](https://github.com/mjkuranda): Each game over resets data.

### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Missile collisions.

## [0.5.0] - 2023-06-26
### Changed
- [@mjkuranda](https://github.com/mjkuranda): GameMap to GameData.
- [@mjkuranda](https://github.com/mjkuranda): Spawn and destroy refactored.

### Fixed
- [@mjkuranda](https://github.com/mjkuranda): ArrayOutOfBoundsException related to player movement and missiles.
- [@mjkuranda](https://github.com/mjkuranda): Building artifacts again.

## [0.4.0] - 2023-06-23
### Added
- [@mjkuranda](https://github.com/mjkuranda): GameMap and Renderer abstract classes.
- [@mjkuranda](https://github.com/mjkuranda): ArcadeRenderer that renders 2D map.
- [@mjkuranda](https://github.com/mjkuranda): Basic Player class and handling keys.
- [@mjkuranda](https://github.com/mjkuranda): Player can shoot LaserMissiles. 
- [@mjkuranda](https://github.com/mjkuranda): Colliding asteroids and the player.
- [@mjkuranda](https://github.com/mjkuranda): Colliding player missiles with asteroids.

## [0.3.0] - 2023-06-22
### Added
- [@mjkuranda](https://github.com/mjkuranda): Adopting window to the screen size and detecting its.
- [@mjkuranda](https://github.com/mjkuranda): Entity, SpaceEntity and Missile as abstract classes.
- [@mjkuranda](https://github.com/mjkuranda): Asteroid and LaserMissile as new derived classes.
- [@mjkuranda](https://github.com/mjkuranda): Moveable, Destroyable, Damageable and Shootable interfaces.
- [@mjkuranda](https://github.com/mjkuranda): Entity turn to define if it's incoming or outcoming entity.
- [@mjkuranda](https://github.com/mjkuranda): Entity type defining a specific type of the entity.
- [@mjkuranda](https://github.com/mjkuranda): Shootable as Strategy Pattern.

## [0.2.0] - 2023-06-21
### Added
- [@mjkuranda](https://github.com/mjkuranda): Simple game state system.

## [0.1.0] - 2023-06-21
### Added
- [@mjkuranda](https://github.com/mjkuranda): Readme file.

## [0.0.1] - 2023-06-21
### Fixed
- [@mjkuranda](https://github.com/mjkuranda): Remove from git all files from `/out` directory.

## [0.0.0] - 2023-06-21
### Added
- [@mjkuranda](https://github.com/mjkuranda): Runnable and compilable game.