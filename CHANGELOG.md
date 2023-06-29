# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.8.0] - 2023-06-29
### Added
- [@mjkuranda](https://github.com/mjkuranda): xxx

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