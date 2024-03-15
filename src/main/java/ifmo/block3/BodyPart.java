package ifmo.block3;

import ifmo.block3.exceptions.AlreadyEngagedException;
import ifmo.block3.exceptions.NoInteractionException;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BodyPart {
    private final String name;
    private final int length;
    private final int weight;
    private final BodyPartEnum bodyPartEnum;
    private boolean inAction = false;
    private BodyPart interactionBodyPart;

    public void interact(BodyPart bodyPart, String actionDescription) throws AlreadyEngagedException {
        if (this.inAction || bodyPart.isInAction()) {
            throw new AlreadyEngagedException("часть тела уже занята");
        }
        this.inAction = true;
        setInteractionBodyPart(bodyPart);
        bodyPart.setInteractionBodyPart(this);
        bodyPart.setInAction(true);
        System.out.println(actionDescription);
    }

    public void endInteraction(BodyPart bodyPart) throws NoInteractionException {
        if (!interactionBodyPart.equals(bodyPart)
                || !equals(bodyPart.getInteractionBodyPart())) {
            throw new NoInteractionException("эти части тела не взаимодействовали");
        }
        this.inAction = false;
        setInteractionBodyPart(null);
        bodyPart.setInteractionBodyPart(null);
        bodyPart.setInAction(false);
    }
}
