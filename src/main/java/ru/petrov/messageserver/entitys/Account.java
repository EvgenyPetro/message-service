package ru.petrov.messageserver.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate registerDate;
    private String phone;
    private String picture;
    private String location;

    @JsonIgnore
    @ManyToMany
    private Collection<Account> parentAccount = new HashSet<>(0);

    @JsonBackReference
    @ManyToMany(mappedBy = "parentAccount")
    private Collection<Account> friends = new HashSet<>(0);

    public void addFriend(Account friend) {
        this.friends.add(friend);
        friend.getParentAccount().add(this);
    }

}
