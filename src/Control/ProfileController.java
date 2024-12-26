package Control;

import Model.User;
import Model.*;
import View.ProfilePanel;

public class ProfileController {
    Users users;
    ProfilePanel profilePanel;
    ProfileController(Users users ,ProfilePanel profilePanel)
    {
        this.users = users;
        this.profilePanel =profilePanel;

    }

}
