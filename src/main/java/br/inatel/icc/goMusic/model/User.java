package br.inatel.icc.goMusic.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String avatar;
	private String country;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Profile> profiles = new ArrayList<>();

	@OneToMany(mappedBy = "owner", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Playlist> myPlaylists = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Like> likedPlaylists = new ArrayList<>();

	@OneToMany(mappedBy = "following", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Follow> followings = new ArrayList<>();

	@OneToMany(mappedBy = "follower", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Follow> followers = new ArrayList<>();

	public User(String name, String email, String password, String avatar, String country) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setMyPlaylists(List<Playlist> myPlaylists) {
		this.myPlaylists = myPlaylists;
	}

	public List<User> getFollowers() {
		List<User> users = new ArrayList<>();

		followers.forEach(userFollow -> {
			users.add(userFollow.getFollowing());
		});

		return users;
	}

	public List<Playlist> getLikedPlaylists() {
		List<Playlist> playlists = new ArrayList<>();

		likedPlaylists.forEach(playlistLiked -> {
			playlists.add(playlistLiked.getPlaylist());
		});

		return playlists;
	}

	public void setLikedPlaylists(List<Like> liked) {
		this.likedPlaylists = liked;
	}

	public void setFollowers(List<Follow> followers) {
		this.followers = followers;
	}

	public List<User> getFollowings() {
		List<User> users = new ArrayList<>();

		followings.forEach(userFollow -> {
			users.add(userFollow.getFollower());
		});

		return users;
	}

	public void setFollowings(List<Follow> followings) {
		this.followings = followings;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profiles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Playlist> getMyPlaylists() {
		return myPlaylists;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
