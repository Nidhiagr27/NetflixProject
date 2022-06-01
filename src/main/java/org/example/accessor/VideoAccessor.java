package org.example.accessor;

import org.example.accessor.models.VideoDTO;
import org.example.exceptions.DependencyFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class VideoAccessor {

    @Autowired
    DataSource dataSource;

    public VideoDTO getVideoByVideoId(final String videoId){
        String query="SELECT name,seriesId,showId,rating,releaseDate,totalLength,videoPath,thumbnailPath from videos where videoId=?";
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement pstmt=connection.prepareStatement(query);
            pstmt.setString(1,videoId);
            ResultSet resultSet= pstmt.executeQuery();
            if(resultSet.next()){
                VideoDTO videoDTO=VideoDTO.builder()
                        .videoId(videoId)
                        .name(resultSet.getString(1))
                        .seriesId(resultSet.getString(2))
                        .showId(resultSet.getString(3))
                        .rating(resultSet.getDouble(4))
                        .releaseDate(resultSet.getDate(5))
                        .totalLength(resultSet.getInt(6))
                        .videoPath(resultSet.getString(7))
                        .thumbnailPath(resultSet.getString(8))
                        .build();
                return videoDTO;
            }
            return null;
        }
        catch(SQLException ex){
            ex.printStackTrace();
            throw new DependencyFailureException(ex);
        }
    }
}
