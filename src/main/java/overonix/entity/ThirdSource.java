package overonix.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "the_third_source")
public class ThirdSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String apikey;
    String url;
    @OneToOne(targetEntity = CurrencyDetails.class)
    CurrencyDetails details;

    public ThirdSource(String apikey, String url) {
        this.apikey = apikey;
        this.url = url;
    }
}
