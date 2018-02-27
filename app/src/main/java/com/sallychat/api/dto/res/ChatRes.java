package com.sallychat.api.dto.res;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Swetarani Panda on 2/27/2018.
 */

public class ChatRes {
    public Response response;
    public String extra;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }


    public class Response {
        public Input input;
        public OutPut output;
        List<Intent> intents = new ArrayList<>();
        List<Entity> entities = new ArrayList<>();
        Context context;

        public List<Entity> getEntities() {
            return entities;
        }

        public void setEntities(List<Entity> entities) {
            this.entities = entities;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public List<Intent> getIntents() {
            return intents;
        }

        public void setIntents(List<Intent> intents) {
            this.intents = intents;
        }

        public Input getInput() {
            return input;
        }

        public void setInput(Input input) {
            this.input = input;
        }

        public OutPut getOutput() {
            return output;
        }

        public void setOutput(OutPut output) {
            this.output = output;
        }
    }

    public class Input {
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String text;
    }

    public class OutPut {
        public ArrayList<String> getText() {
            return text;
        }

        public void setText(ArrayList<String> text) {
            this.text = text;
        }

        public ArrayList<String> text;

        public ArrayList<String> getLog_messages() {
            return log_messages;
        }

        public void setLog_messages(ArrayList<String> log_messages) {
            this.log_messages = log_messages;
        }

        public ArrayList<String> log_messages;

        public ArrayList<String> getNodes_visited() {
            return nodes_visited;
        }

        public void setNodes_visited(ArrayList<String> nodes_visited) {
            this.nodes_visited = nodes_visited;
        }

        public ArrayList<String> nodes_visited;

    }

    class Intent {
        String intent;

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

    }

    class Entity {
        String entity;
        String value;
        Integer confidence;
        List<Integer> location = new ArrayList<>();

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getConfidence() {
            return confidence;
        }

        public void setConfidence(Integer confidence) {
            this.confidence = confidence;
        }

        public List<Integer> getLocation() {
            return location;
        }

        public void setLocation(List<Integer> location) {
            this.location = location;
        }

        public String getEntity() {

            return entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }
    }

    class Context {
        System system;

        public System getSystem() {
            return system;
        }

        public void setSystem(System system) {
            this.system = system;
        }

        public String getConversation_id() {
            return conversation_id;
        }

        public void setConversation_id(String conversation_id) {
            this.conversation_id = conversation_id;
        }

        String conversation_id;

    }

    class System {
        public int getDialog_turn_counter() {
            return dialog_turn_counter;
        }

        public void setDialog_turn_counter(int dialog_turn_counter) {
            this.dialog_turn_counter = dialog_turn_counter;
        }

        public int getDialog_request_counter() {
            return dialog_request_counter;
        }

        public void setDialog_request_counter(int dialog_request_counter) {
            this.dialog_request_counter = dialog_request_counter;
        }

        public String getBranch_exited_reason() {
            return branch_exited_reason;
        }

        public void setBranch_exited_reason(String branch_exited_reason) {
            this.branch_exited_reason = branch_exited_reason;
        }

        public boolean isBranch_exited() {
            return branch_exited;
        }

        public void setBranch_exited(boolean branch_exited) {
            this.branch_exited = branch_exited;
        }

        public ArrayList<Stack> getDialog_stack() {
            return dialog_stack;
        }

        public void setDialog_stack(ArrayList<Stack> dialog_stack) {
            this.dialog_stack = dialog_stack;
        }

        int dialog_turn_counter;
        int dialog_request_counter;
        String branch_exited_reason;
        boolean branch_exited;
        ArrayList<Stack> dialog_stack;
    }

    class Stack {
        public String getDialog_node() {
            return dialog_node;
        }

        public void setDialog_node(String dialog_node) {
            this.dialog_node = dialog_node;
        }

        String dialog_node;
    }

}
