/*
 * Copyright 2016 John Grosh <john.a.grosh@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.jagrosh.jmusicbot.commands;

import me.jagrosh.jdautilities.commandclient.CommandEvent;
import me.jagrosh.jmusicbot.Bot;
import me.jagrosh.jmusicbot.audio.AudioHandler;
import net.dv8tion.jda.core.entities.User;

/**
 *
 * @author John Grosh <john.a.grosh@gmail.com>
 */
public class ForceskipCmd extends MusicCommand {

    public ForceskipCmd(Bot bot)
    {
        super(bot);
        this.name = "forceskip";
        this.help = "skips the current song";
        this.aliases = new String[]{"modskip"};
        this.bePlaying = true;
        this.category = bot.DJ;
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();
        User u = event.getJDA().getUserById(handler.getCurrentTrack().getIdentifier());
        event.reply(event.getClient().getSuccess()+" Skipped **"+handler.getCurrentTrack().getTrack().getInfo().title
                +"** (requested by "+(u==null ? "someone" : "**"+u.getName()+"**")+")");
        handler.getPlayer().stopTrack();
    }
    
}
