package Main;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	public String prefix = "<";
	
	@SuppressWarnings("deprecation")
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		//Alles Standard Commands
		if (args[0].equalsIgnoreCase(prefix + "test")) {
			event.getMessage().reply("I am alive").queue();
		}
		if (args[0].equalsIgnoreCase(prefix + "test2")) {
			event.getMessage().reply("Sierra Papa Alpha Sierra Tango").queue();
		}
		if (args[0].equalsIgnoreCase(prefix + "test3")) {
			event.getMessage().reply("Placeholder").queue();
		}
		//Ab hier Embeds (Diese fancy Nachrichten)
		if (args[0].equalsIgnoreCase(prefix + "test4")) {
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("EmbedTitle", "");
			embed.setDescription("Claas Schwester ist geil");
			embed.addField("Embed Field1", "Content von Field 1", false);
			embed.addField("Embed Field2", "Content von Field 2", false);
			embed.setFooter("Bot created by <@!402125967595929600>");
			event.getMessage().reply(embed.build()).queue();
			embed.clear();
							
		}
		//Rollencommand
		if (args[0].equalsIgnoreCase(prefix + "giverole")) {
			if (event.getMessage().getMentionedRoles().toArray().length == 1) {
				if (event.getMessage().getMentionedUsers().toArray().length == 1) {
					Member member = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
					Role roleToGive = event.getMessage().getMentionedRoles().get(0);
					event.getGuild().addRoleToMember(member, roleToGive).queue();
					event.getMessage().reply("Gave the role" + roleToGive.getAsMention() + " to " + member.getAsMention()).queue();
				} else {
					event.getMessage().reply("Please mention ONE User!").queue();
				}
			} else {
				event.getMessage().reply("Please mention ONE role!").queue();
			}
		}
		
		if (args[0].equalsIgnoreCase(prefix + "removerole")) {
			if (event.getMessage().getMentionedRoles().toArray().length == 1) {
				if (event.getMessage().getMentionedUsers().toArray().length == 1) {
					Member member = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
					Role roleToRemove = event.getMessage().getMentionedRoles().get(0);
					event.getGuild().removeRoleFromMember(member, roleToRemove).queue();
					event.getMessage().reply("Removed the role" + roleToRemove.getAsMention() + " from " + member.getAsMention()).queue();
				} else {
					event.getMessage().reply("Please mention ONE User!").queue();
				}
			} else {
				event.getMessage().reply("Please mention ONE role!").queue();
			}
		}		
	}	
}