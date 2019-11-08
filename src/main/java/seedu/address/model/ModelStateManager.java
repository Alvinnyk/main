package seedu.address.model;

import java.util.Stack;

public class ModelStateManager {

    private Stack<ModelState> states;

    public ModelStateManager() {
        this.states = new Stack<>();
    }

    public void saveState(ModelState modelState) {

        states.push(modelState);

    }

    public ModelState undo() throws NothingToUndoException {
        if(states.size() <= 1) {
            throw new NothingToUndoException();
        } else {
            states.pop();

            return states.peek();
        }
    }

}
