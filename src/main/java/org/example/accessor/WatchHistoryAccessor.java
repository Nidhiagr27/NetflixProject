package org.example.accessor;

import org.example.accessor.models.WatchHistoryDTO;
import org.example.exceptions.DependencyFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class WatchHistoryAccessor {

    @Autowired
    private DataSource dataSource;

    public void updateWatchedLength(final String profileId,final String videoId,final int watchedLength){
        String query= "UPDATE watchHistory set watchedLength=? , lastWatchedAt=? where profileId=? and "+
                "videoId=?";
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,watchedLength);
            pstmt.setDate(2,new Date(System.currentTimeMillis()));
            pstmt.setString(3,profileId);
            pstmt.setString(4,videoId);
            pstmt.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new DependencyFailureException(ex);
        }
    }

    public void insertWatchHistory(final String profileId,final String videoId,final double rating,
                                   final int watchedLength){
        String query="INSERT into watchHistory values(?, ?, ?, ?, ?, ?)";
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement pstmt= connection.prepareStatement(query);
            pstmt.setString(1,profileId);
            pstmt.setString(2,videoId);
            pstmt.setDouble(3,rating);
            pstmt.setDate(4,new Date(System.currentTimeMillis())); //current date
            pstmt.setDate(5,new Date(System.currentTimeMillis()));
            pstmt.setInt(6,watchedLength);
            pstmt.execute();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            throw new DependencyFailureException(ex);
        }
    }

    public WatchHistoryDTO getWatchHistory(final String profileId, final String videoId){
       String query="SELECT rating,lastWatchedAt,firstWatchedAt,watchedLength from watchHistory where profileId=? "+ " and videoId=?";
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement pstmt= connection.prepareStatement(query);
            pstmt.setString(1,profileId);
            pstmt.setString(2,videoId);
            ResultSet resultSet= pstmt.executeQuery();
            if(resultSet.next()){
                 WatchHistoryDTO watchHistoryDTO= WatchHistoryDTO.builder()
                         .profileId(profileId).videoId(videoId).rating(resultSet.getDouble(1))
                         .lastWacthedAt(resultSet.getDate(2))
                         .firstWatchedAt(resultSet.getDate(3))
                         .watchedLength(resultSet.getInt(4))
                         .build();
                 return watchHistoryDTO;
            }
            else{
                return null;
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
            throw new DependencyFailureException(ex);
        }
    }
}
