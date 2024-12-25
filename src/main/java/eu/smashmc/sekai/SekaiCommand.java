package eu.smashmc.sekai;

import de.liquiddev.command.CommandFailException;
import de.liquiddev.command.adapter.bukkit.Arguments;
import de.liquiddev.command.adapter.bukkit.ConsoleCommand;
import eu.smashmc.api.concurrent.AsyncExecutor;
import eu.smashmc.api.core.Inject;
import eu.smashmc.api.core.Managed;
import eu.smashmc.lib.bukkit.concurrent.BukkitExecutor;
import eu.smashmc.lib.bukkit.world.VoidWorld;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.UnzipParameters;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

@Managed
public class SekaiCommand extends ConsoleCommand {

	@Inject
	private Plugin plugin;

	public SekaiCommand() {
		super("sekai", "<load/unload>");
	}

	@Override
	protected void onCommand(CommandSender sender, Arguments args) throws CommandFailException {

		String worldName = args.get(1);

		if (args.check(0, "load")) {


		} else if (args.checkOptional(0, "unload")) {


		}
	}

	private CompletableFuture<World> loadWorld(String worldId, boolean populateChunks) throws IOException {
		Path worldPath = Paths.get(worldId);
		Path zipPath = Paths.get(worldId + ".zip");
		if (Files.exists(worldPath)) {
			throw new IllegalArgumentException("There already is a folder for world " + worldId);
		}
		return unzipWorldAsync(worldId, zipPath).thenRun(() -> {
			if (!Files.exists(worldPath)) {
				throw new IllegalArgumentException("No files unzipped to " + worldPath.toString());
			}
		}).thenApplyAsync(v -> {
			var worldGen = new VoidWorld();
			var world = worldGen.generate(worldId);
			if (populateChunks) {
				worldGen.populateChunks(world.getSpawnLocation(), Bukkit.getServer().getViewDistance() + 2);
			}
			for (Player all : Bukkit.getOnlinePlayers()) {
				all.teleport(world.getSpawnLocation());
			}
			return world;
		}, BukkitExecutor.getSyncExecutor(plugin));
	}

	private void unloadWorld(String name) {


	}

	private CompletableFuture<Void> unzipWorldAsync(String worldId, Path zipFilePath) {
		return AsyncExecutor.supply(() -> {
			try (var zipFile = new ZipFile(zipFilePath.toFile())) {
				var params = new UnzipParameters();
				params.setExtractSymbolicLinks(false);
				zipFile.extractAll(worldId, params);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}
}
