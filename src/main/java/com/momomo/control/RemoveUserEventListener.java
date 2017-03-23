package com.momomo.control;

        import com.momomo.model.User;
        import com.momomo.view.MainPage;
        import com.momomo.view.UserManPage;
        import com.vaadin.server.Page;
        import com.vaadin.ui.*;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;

        import javax.xml.soap.Text;

/**
 * Created by Charberg on 2/19/2017.
 */
@Getter
@Setter
public class RemoveUserEventListener implements Button.ClickListener{

    private final UserRepositoryInterface userRepo;
    private final UserManPage page;
    private ListSelect list;

    //Pass in text fields to get values at moment of click
    public RemoveUserEventListener(UserRepositoryInterface userRepo, UserManPage page, ListSelect list) {
        this.userRepo = userRepo;
        this.page = page;
        this.list = list;
    }

    // The listener method implementation
    public void buttonClick(Button.ClickEvent event) {
        String val = (String)list.getValue();
        User user = userRepo.getUserByUserName(val);
        if (user.equals(page.getCurrentUser())){
            Notification notification = new Notification("Cannot remove current user");
            notification.setDelayMsec(2000);
            notification.show(Page.getCurrent());
            return;
        }
        userRepo.removeUser(val);
        page.updateUsersList(userRepo.getAllUsers());
    }

}
