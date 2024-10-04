package Models;

import CommonFunctions.Command;
import CommonFunctions.ExceptionLogHandler;
import Controllers.GameEngineController;
import Exceptions.InvalidCommand;
import Exceptions.InvalidMap;
import Services.GameService;
import Views.ShowMap;

import java.io.IOException;
import CommonFunctions.Common;
import Constants.Constants;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the Order Execution Phase for GamePlay using the State
 * Pattern.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class OrderExecutionPhase extends GamePlayPhase {

	/**
	 * This is a constructor responsible for initializing the GameEngine context
	 * within the Phase class.
	 *
	 * @param p_gameEngineController Instance of the game engine used to update the
	 *                               state.
	 * @param p_gameState            The current game state associated with the game
	 *                               engine instance.
	 */
	public OrderExecutionPhase(GameEngineController p_gameEngineController, GameState p_gameState) {
		super(p_gameEngineController, p_gameState);
	}

    @Override
	protected void executeCardAction(String p_enteredCommand, Player p_player) throws IOException {
		LogInvalidCommandInCurrentPhase();
	}

    @Override
	public void initPhase(boolean isTournamentMode) {
		executeOrders();
		
		ShowMap l_map_view = new ShowMap(d_gameState);
		l_map_view.showMap();
		
		if (this.checkGameHasEnded(d_gameState))
			return;
		
		try {
			String l_continue = this.continueForNextTurn(isTournamentMode);
			
			if (l_continue.equalsIgnoreCase("N") && isTournamentMode) {
				d_gameEngineController.setD_gameEngineControllerLog("Startup Phase", Constants.GAMEPLAY_PHASE);
				d_gameEngineController.setD_CurrentPhase(new StartUpPhase(d_gameEngineController, d_gameState));
			} else if (l_continue.equalsIgnoreCase("N") && !isTournamentMode) {
				d_gameEngineController.setGamePlayPhase(Constants.START_UP_PHASE, isTournamentMode);
			} else if (l_continue.equalsIgnoreCase("Y")) {
				System.out.println("\n" + d_gameState.getD_numberOfTurnsLeft()
						+ " Turns are left for this game. Continuing for next Turn.\n");
				d_playerService.allocArmies(d_gameState);
				d_gameEngineController.setGamePlayPhase(Constants.ISSUE_ORDER_PHASE, isTournamentMode);
			} else {
				System.out.println("Invalid Input");
			}
		} catch (IOException l_e) {
			System.out.println("Invalid Input");
		}
	}
	
	/**
	 * Checks if next turn has to be played or not.
	 *
	 * @param isTournamentMode if tournament is being played
	 * @return Yes or no based on user input or tournament turns left
	 * @throws IOException indicates failure in I/O operation
	 */
	private String continueForNextTurn(boolean isTournamentMode) throws IOException {
		String l_continue = new String();
		if (isTournamentMode) {
			d_gameState.setD_numberOfTurnsLeft(d_gameState.getD_numberOfTurnsLeft() - 1);
			l_continue = d_gameState.getD_numberOfTurnsLeft() == 0 ? "N" : "Y";
		} else {
			System.out.println("Press Y/y if you want to continue for next turn or else press N/n");
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(System.in));
			l_continue = l_reader.readLine();
		}
		return l_continue;
	}
	
	
	/**
	 * Invokes order execution logic for all unexecuted orders.
	 */
	protected void executeOrders() {
		// addNeutralPlayer(d_gameState);
		// Executing orders
		d_gameEngineController.setD_gameEngineControllerLog("\nStarting Execution Of Orders.....", Constants.START_GAME);
		while (d_playerService.pendingOrdersExist(d_gameState.getD_playerList())) {
			for (Player l_player : d_gameState.getD_playerList()) {
				Order l_order = l_player.next_order();
				if (l_order != null) {
					l_order.printOrder();
					d_gameState.updateLog(l_order.orderExecutionLog(), Constants.ORDER_EFFECT);
					l_order.execute(d_gameState);
				}
			}
		}
		d_playerService.resetPlayersFlag(d_gameState.getD_playerList());
	}

    /**
	 * Add neutral player to game state.
	 *
	 * @param p_gameState GameState
	 */
	public void addNeutralPlayer(GameState p_gameState) {
		Player l_player = p_gameState.getD_playerList().stream()
				.filter(l_pl -> l_pl.getD_playerName().equalsIgnoreCase("Neutral")).findFirst().orElse(null);
		if (Common.isNull(l_player)) {
			Player l_neutralPlayer = new Player("Neutral");
			l_neutralPlayer.setD_additionalOrders(false);
			p_gameState.getD_playerList().add(l_neutralPlayer);
		} else {
			return;
		}
	}

    @Override
	protected void executeShowMap(Command p_command, Player p_player) {
		ShowMap l_showMap = new ShowMap(d_gameState);
		l_showMap.showMap();
	}

	@Override
	protected void executeDeploy(String p_command, Player p_player) {
		LogInvalidCommandInCurrentPhase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executeAssignCountries(Command p_command, Player p_player, boolean isTournamentMode,
										  GameState p_gameState) throws InvalidCommand, IOException {
		LogInvalidCommandInCurrentPhase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executeGameplayer(Command p_command, Player p_player) throws InvalidCommand {
		LogInvalidCommandInCurrentPhase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executeEditNeighbour(Command p_command, Player p_player)
			throws InvalidCommand, InvalidMap, IOException {
		LogInvalidCommandInCurrentPhase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executeValidateMap(Command p_command, Player p_player) throws InvalidMap, InvalidCommand {
		LogInvalidCommandInCurrentPhase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executeLoadMap(Command p_command, Player p_player) throws InvalidCommand, InvalidMap {
		LogInvalidCommandInCurrentPhase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executeSaveMap(Command p_command, Player p_player) throws InvalidCommand, InvalidMap {
		LogInvalidCommandInCurrentPhase();
	}

	@Override
    protected void executeAdvance(String p_command, Player p_player) {
        LogInvalidCommandInCurrentPhase();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executeEditContinent(Command p_command, Player p_player)
			throws IOException, InvalidCommand, InvalidMap {
		LogInvalidCommandInCurrentPhase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executeMapEdit(Command p_command, Player p_player) throws IOException, InvalidCommand, InvalidMap {
		LogInvalidCommandInCurrentPhase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executeEditCountry(Command p_command, Player p_player)
			throws IOException, InvalidCommand, InvalidMap {
		LogInvalidCommandInCurrentPhase();
	}

	/**
	 * If a player owns all countries of the map, it indicates that the game has
	 * ended.
	 *
	 * @param p_gameState Current State of the game
	 * @return true if game has to be ended else false
	 */
	protected Boolean checkGameHasEnded(GameState p_gameState) {
		Integer l_totalCountries = p_gameState.getD_map().getD_countries().size();
		for (Player l_player : p_gameState.getD_playerList()) {
			if (l_player.getD_playerCountries().size() == l_totalCountries) {
				// d_gameEngineController.setd_gameEngineControllerLog("Player : " +
				// l_player.getD_playerName()
				// + " has won the Game by conquering all countries. Exiting the Game .....",
				// Constants.END_GAME);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executeLoadGame(Command p_command, Player p_player) throws InvalidCommand, InvalidMap, IOException {
		LogInvalidCommandInCurrentPhase();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executeSaveGame(Command p_command, Player p_player) throws InvalidCommand, InvalidMap, IOException {
		List<java.util.Map<String, String>> l_operations_list = p_command.getOperationsAndArguments();
		
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionLogHandler(d_gameState));
		
		if (l_operations_list == null || l_operations_list.isEmpty()) {
			throw new InvalidCommand(Constants.INVALID_SAVEGAME_CMD_ERROR);
		}
		
		for (Map<String, String> l_map : l_operations_list) {
			if (p_command.checkRequiredKeysPresent(Constants.ARGUMENTS, l_map)) {
				String l_filename = l_map.get(Constants.ARGUMENTS);
				GameService.saveGame(this, l_filename);
				d_gameEngineController.setD_gameEngineControllerLog("Game Saved Successfully to " + l_filename,
						Constants.ORDER_EFFECT);
			} else {
				throw new InvalidCommand(Constants.INVALID_SAVEGAME_CMD_ERROR);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void executetournamentGamePlay(Command p_enteredCommand) throws InvalidCommand, InvalidMap {
		LogInvalidCommandInCurrentPhase();
	}

}
