package jp.co.internous.gaia.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.gaia.model.domain.dto.PurchaseHistoryDto;

@Mapper
public interface TblPurchaseHistoryMapper {
	int insert(Map<String, Object> parameter);
	List<PurchaseHistoryDto> findByUserId(@Param("userId") int userId);
	
/*アップデート文でDBのstatusを0に更新*/
	@Update("UPDATE tbl_purchase_history SET status = 0, updated_at = now() WHERE user_id = #{userId}")
	int logicalDeleteByUserId(@Param("userId") int userId);
}