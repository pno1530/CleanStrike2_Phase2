package app.com.service

trait RulesApplicable[PlayerId, GameState] {

    def applyRules(playerId: PlayerId): GameState => GameState

}



