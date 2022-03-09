# Plugin template

This example plugin should be used whenever creating a spigot or bungeecord plugin for SmashMC.

## Quickstart
1. Clone repository using `git clone git@github.com:SmashMCeu/plugin-template.git <plugin-name>`
2. Rename `eu.smashmc.example` to `eu.smashmc.<lowercase-plugin-name>`
3. Rename `ExamplePlugin.java` to `<plugin-name>Plugin.java`
4. Change `artifactId`, `name`, `description` and `bukkit.main` properties in `pom.xml` to fit your plugin
5. Run `mvn clean package` to build your plugin
