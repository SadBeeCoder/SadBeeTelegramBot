package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {
    // private String gamerName = null;

    private boolean screaming = false;
    private boolean startgame = false;
    private HashMap<Long, UserState> userStates = new HashMap<>(); // HashMap to store UserState for each chatID
    String token = "XXXXXXXXXXXXXXXXXXXXXXX";

    @Override
    public String getBotUsername() {
        return "SadBeeBot";
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public Bot() {
    }

    //Kommentar
    @Override
    public void onUpdateReceived(Update update) {
        Long chatID = update.getMessage().getChatId();

        // Retrieve the user's state from the HashMap or create a new one if not exists
        UserState userState = userStates.getOrDefault(chatID, new UserState());

        userState.className[0] = "Fighter";
        userState.className[1] = "Mage";
        userState.className[2] = "Ranger";

        var msg = update.getMessage().getText();
        var user = update.getMessage().getFrom();
        var id = user.getId();

        while (userState.getState() == State.START) {
            System.out.println(msg);
            if (msg.equals("/startgame")) {
                if (userState.getProgress() == 0) {
                    userState.setProgress(1);
                    sendText(chatID, "Welcome to the SadBeeRPG!");
                    sendText(chatID, "Please enter your character name:");
                    break;
                }
            }
            if (userState.getProgress() == 1) {
                System.out.println(msg);
                userState.setGamerName(msg);
                userState.setProgress(2);

            }
            if (userState.getProgress() == 2) {
                sendText(chatID, "You Character is: " + userState.getGamerName() ); // + chatID
                userState.setProgress(0);
                userState.setState(State.RUNNING);
                break;

            } else {
                sendText(chatID, "Please enter your name again!");
                userState.setProgress(1);
                break;
            }
        }

        while (userState.getState() == State.RUNNING) {

            if (userState.getProgress() == 0) {
                userState.setProgress(1);
                sendText(chatID, "What class do you want to play?");
                sendText(chatID, "Fighter ⚔️");
                sendText(chatID, "100 Health❤️");
                sendText(chatID, "10  Strength💪");
                sendText(chatID, "7   Defense🛡️");
                sendText(chatID, "4   Dexterity");
                sendText(chatID, "1   Magic");
                sendText(chatID, "_________________");
                sendText(chatID, "Mage  🧙‍♂️️️");
                sendText(chatID, "100 Health❤️");
                sendText(chatID, "1   Strength💪");
                sendText(chatID, "4   Defense🛡️");
                sendText(chatID, "5   Dexterity");
                sendText(chatID, "12  Magic");
                sendText(chatID, "_________________");
                sendText(chatID, "Ranger 🏹");
                sendText(chatID, "100 Health❤️");
                sendText(chatID, "4   Strength💪");
                sendText(chatID, "5   Defense🛡️");
                sendText(chatID, "10  Dexterity👣");
                sendText(chatID, "3   Magic🪄");
                sendText(chatID, "Press 0 for fighter, 1 for mage, 2 for ranger");
                break;
            }
            if (userState.getProgress() == 1) {
                if (msg.equals("0")) {
                    userState.setChosenClass(userState.className[0]);
                    sendText(chatID, "You chose " + userState.className[0]);
                    sendText(chatID, "What do you want to do:");
                    sendText(chatID, "Write 0 to search for a dungeon");
                    sendText(chatID, "Write 1 to travel to the closest city");
                    userState.setProgress(2);
                    break;
                }
                if (msg.equals("1")) {
                    userState.setChosenClass(userState.className[1]);
                    sendText(chatID, "You chose " + userState.className[1]);
                    sendText(chatID, "What do you want to do:");
                    sendText(chatID, "Write 0 to search for a dungeon");
                    sendText(chatID, "Write 1 to travel to the closest city");
                    userState.setProgress(2);
                    break;
                }
                if (msg.equals("2")) {
                    userState.setChosenClass(userState.className[2]);
                    sendText(chatID, "You chose " + userState.className[2]);
                    sendText(chatID, "What do you want to do:");
                    sendText(chatID, "Write 0 to search for a dungeon");
                    sendText(chatID, "Write 1 to travel to the closest city");
                    userState.setProgress(2);
                    break;
                } else {
                    userState.setProgress(1);
                    sendText(chatID, "That wasn't an option you fool!");
                    break;
                }
            }
            if (userState.getProgress() == 2) {
                if (msg.equals("0")) {
                    userState.setProgress(3);
                    sendText(chatID, "You found a Dungeon! Do you want to enter(0) or return(1)?");
                  // userState.setState(State.START);
                    break;

                }
                if (msg.equals("1")) {
                    userState.setProgress(3);
                    sendText(chatID, "You found a city! Do you want to enter or return?");
                   // userState.setState(State.START);
                    break;
                }
            }
            if (userState.getProgress() == 3) {
                if (msg.equals("0")) {
                    userState.setProgress(4);
                    userState.setState(State.START);
                    sendText(chatID,"You encountered a old red dragon that looks you straight in the eye the moment you turned the first corner!");
                    sendText(chatID, "You were swallowed in a heartbeat - YOU ARE DEAD WHY ARE YOU EVEN AN ADVENTURER GO BACK HOME OUT OF THE BASEMENT OF YOUR PARENTS AND SEARCH FOR A NORMAL JOB LIKE EVERYBODY ELSE SMH! 😒");
                    break;
                }
            }

        }
         /*if(screaming) //returns message in uppercase cuz he mad
        {
            scream(id, update.getMessage());
        }else
        {
            copyMessage(id, msg.getMessageId());
        }
        if(msg.isCommand()){
            if(msg.getText().equals("/scream"))         //If the command was /scream, we switch gears
                screaming = true;
            else if (msg.getText().equals("/whisper"))  //Otherwise, we return to normal
                screaming = false;

            return;                                     //We don't want to echo commands, so we exit
        }*/

        /*if (msg.isCommand()) {
            if (msg.getText().equals("/startgame")) {
                sendText(id, "Please enter your character name.");
                gamerName = null; //reset gamer name
            } else if (msg.getText().equals("/endgame")) {
                startgame = false;
            }
        } else if (startgame) {
            if (gamerName == null) {
                gamerName = msg.getText();  //set the new gamer name
                gamerName = update.getMessage().getText();
                sendText(id, "You are " + gamerName);
            } else {
                sendText(id, "You are already " + gamerName);
            }
        } */
        System.out.println(user.getFirstName() + " wrote: " + msg);
        // Store the updated userState back into the HashMap after modifying it
        userStates.put(chatID, userState);
    }

    public void sendText(Long chatID, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(chatID.toString()) // Who are we sending the message to?
                .text(what).build(); // Message content
        try {
            execute(sm); // Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e); // Any error will be printed here
        }
    }

    /* public void copyMessage(Long who, Integer msgId) {
        CopyMessage cm = CopyMessage.builder()
                .fromChatId(who.toString()) //We copy from the user
                .chatId(who.toString()) //and send it back to him
                .messageId(msgId) //specifying what message
                .build();
        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void scream(Long id, Message msg) {
        if(msg.hasText()) {
            sendText(id, msg.getText().toUpperCase());
        } else {
            copyMessage(id, msg.getMessageId());
        }
    }*/
}
