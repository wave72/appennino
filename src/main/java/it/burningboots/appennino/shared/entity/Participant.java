package it.burningboots.appennino.shared.entity;

import it.burningboots.appennino.shared.AppConstants;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "participant")
public class Participant implements Serializable {
	private static final long serialVersionUID = -7816100274638131540L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
	private Integer id;
	@Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_dt", nullable = false)
	private Date creationDt = null;
	@Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_dt", nullable = false)
	private Date updateDt = null;
	@Basic(optional = false)
	@Column(name = "item_number", length = 64, nullable = false)
	private String itemNumber = "";//Codice personale
	@Column(name = "email", length = 64)
	private String email = "";
	@Column(name = "first_name", length = 64)
	private String firstName = "";
	@Column(name = "last_name", length = 64)
	private String lastName = "";
	@Column(name = "email_original", length = 64)
	private String emailOriginal = "";
	@Column(name = "birth_city", length = 128)
	private String birthCity = "";
    @Temporal(TemporalType.DATE)
	@Column(name = "birth_dt", nullable = false)
	private Date birthDt = null;
	@Column(name = "food_restrictions", length = 2024)
	private String foodRestrictions = "";
	@Column(name = "volunteering", length = 64)
	private String volunteering = "";
	@Basic(optional = false)
	@Column(name = "already_burner", nullable = false)
	private boolean alreadyBurner;
	@Basic(optional = false)
	@Column(name = "already_ibb", nullable = false)
	private boolean alreadyIbb;
	@Column(name = "language", length = 4)
	private String language = "";
	@Column(name = "accommodation_type")
	private Integer accommodationType = AppConstants.ACCOMMODATION_HUT;
	@Column(name = "payment_amount")
	private Double paymentAmount = null;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_dt")
	private Date paymentDt = null;
	@Basic(optional = false)
	@Column(name = "discount", nullable = false)
	private boolean discount;
	
    //@OneToMany(fetch = FetchType.EAGER, mappedBy="participant")
    //private Set<IpnResponse> ipnResponses;
	
	
	public Participant() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public Date getCreationDt() {
		return creationDt;
	}

	public void setCreationDt(Date creationDt) {
		this.creationDt = creationDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getEmailOriginal() {
		return emailOriginal;
	}

	public void setEmailOriginal(String emailOriginal) {
		this.emailOriginal = emailOriginal;
	}

	public String getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public Date getBirthDt() {
		return birthDt;
	}

	public void setBirthDt(Date birthDt) {
		this.birthDt = birthDt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFoodRestrictions() {
		return foodRestrictions;
	}

	public void setFoodRestrictions(String foodRestrictions) {
		this.foodRestrictions = foodRestrictions;
	}

	public String getVolunteering() {
		return volunteering;
	}

	public void setVolunteering(String volunteering) {
		this.volunteering = volunteering;
	}

	public Date getPaymentDt() {
		return paymentDt;
	}

	public void setPaymentDt(Date paymentDt) {
		this.paymentDt = paymentDt;
	}

	//public Set<IpnResponse> getIpnResponses() {
	//	return ipnResponses;
	//}
	//
	//public void setIpnResponses(Set<IpnResponse> ipnResponses) {
	//	this.ipnResponses = ipnResponses;
	//}

	public boolean getAlreadyBurner() {
		return alreadyBurner;
	}

	public void setAlreadyBurner(boolean alreadyBurner) {
		this.alreadyBurner = alreadyBurner;
	}

	public boolean getAlreadyIbb() {
		return alreadyIbb;
	}

	public void setAlreadyIbb(boolean alreadyIbb) {
		this.alreadyIbb = alreadyIbb;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getAccommodationType() {
		return accommodationType;
	}

	public void setAccommodationType(Integer accommodationType) {
		this.accommodationType = accommodationType;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public boolean getDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Participant)) {
            return false;
        }
        Participant other = (Participant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Participant[id=" + id + ", itemNumber="+itemNumber+"]";
    }
}
