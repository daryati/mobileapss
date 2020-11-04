/**
 * 
 */
package id.co.asyst.bukopin.mobile.transfer.core.repository;

import java.math.BigDecimal;
import java.util.Date;

import id.co.asyst.bukopin.mobile.transfer.model.entity.LimitPackage;
import id.co.asyst.bukopin.mobile.transfer.model.entity.TrxLimit;

import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 216930237
 *
 */
@Repository
public interface LimitUserRepository extends JpaRepository<TrxLimit, Long> {
	@Query("SELECT a FROM LimitPackage a WHERE a.id = :limitid ")
	LimitPackage findlimitpckg(@Param("limitid") Long limitid);

	@Query("SELECT new map(b.limitId as limitId,b.id as userid,b.username as username )FROM User b WHERE b.username = :username ")
	JSONObject finduserbyname(@Param("username") String username);

	@Query("SELECT new map(a.id as id,a.accountNumber as accountNumber,"
			+ "a.createDate as createDate,"
			+ "a.totalAmountft as totalAmountft, "
			+ "a.totalAmountob as totalAmountob,"
			+ "a.totalAmountpurchase as totalAmountpurchase,"
			+ "a.totalAmountpayment as totalAmountpayment,"
			+ "a.username as username,a.limitId as limitId,a.menu as menu,"
			+ "a.reason as reason,a.status as status) "
			+ "FROM TrxLimit a WHERE a.accountNumber = :accountNumber "
			+ "AND (a.createDate between  :startday AND  :endday)")
	JSONObject findtrxlimitByAccountNumber(
			@Param("accountNumber") String accountNumber,
			@Param("startday") Date startday, @Param("endday") Date endday);

	@Query("SELECT new map(a.id as userid,a.username as username,a.limitId as limitId,"
			+ "b.dailylimitft as dailylimitft,b.dailylimitob as dailylimitob,"
			+ "b.dailylimitpurchase as dailylimitpurchase,"
			+ "b.dailylimitpayment as dailylimitpayment) from User a "
			+ "left join LimitPackage b on a.limitId=b.id where a.username = :username")
	JSONObject findLimitUserbyUsername(@Param("username") String username);

	// updatte
	@Modifying(clearAutomatically = true)
	@Query("update TrxLimit a set a.totalAmountft =:totalAmountft where a.id =:entryId")
	void updatedailylimitft(@Param("entryId") Long entryId,
			@Param("totalAmountft") BigDecimal totalAmountft);

	@Modifying(clearAutomatically = true)
	@Query("update TrxLimit a set a.totalAmountob =:totalAmountob where a.id =:entryId")
	void updatedailylimitob(@Param("entryId") Long entryId,
			@Param("totalAmountob") BigDecimal totalAmountob);

	@Modifying(clearAutomatically = true)
	@Query("update TrxLimit a set a.totalAmountpurchase =:totalAmountpurchase where a.id =:entryId")
	void updatedailylimitpurchase(@Param("entryId") Long entryId,
			@Param("totalAmountpurchase") BigDecimal totalAmountpurchase);

	@Modifying(clearAutomatically = true)
	@Query("update TrxLimit a set a.totalAmountpayment =:totalAmountpayment where a.id =:entryId")
	void updatedailylimitpayment(@Param("entryId") Long entryId,
			@Param("totalAmountpayment") BigDecimal totalAmountpayment);

	@Modifying
	@Query(value = "DELETE FROM TrxLimit WHERE createDate < :today")
	int deleteDataLimit(@Param("today") Date today);

}
