# Plugin template

This example plugin should be used whenever creating a spigot or bungeecord plugin for SmashMC.

## Quickstart
1. Download and extract template
2. Rename `eu.smashmc.example` to `eu.smashmc.<lowercase-plugin-name>`
3. Rename `ExamplePlugin.java` to `<plugin-name>Plugin.java`
4. Change `artifactId`, `name`, `description` and `bukkit.main` properties in `pom.xml` to fit your plugin
5. Run `mvn clean package` to build your plugin


## Test environment
Setup a local dev environment using:
https://github.com/SmashMCeu/smashmc-dev
