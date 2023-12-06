package com.rhymthwave.ServiceAdmin;

import com.rhymthwave.DAO.*;
import com.rhymthwave.Request.DTO.Top10ArtistDTO;
import com.rhymthwave.Request.DTO.Top10PodcastDTO;
import com.rhymthwave.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {


    private final ArtistDAO artistDAO;

    private final GenreDAO genreDAO;

    private final UserTypeDAO userTypeDAO;

    private  final AdvertismentDAO advertismentDAO;

    private  final  AccountDAO accountDAO;
    public List<Top10ArtistDTO> top10ArtistByListened() {

        return artistDAO.top10ArtistByListened();
    }

    public List<Top10PodcastDTO> top10PodcastByListened() {

        return accountDAO.getTopPodcast();
    }


    public Map<String, List<?>> top4Genre() {
        Map<String, Integer> genreSizeMap = new HashMap<>();

        var listTop4Genre = genreDAO.findAll();

        // Tính toán và thêm kích thước của mỗi thể loại vào Map
        for (Genre genre : listTop4Genre) {
            int size = genre.getSongGenres().size();
            genreSizeMap.put(genre.getNameGenre(), size);
        }

        // Sắp xếp Map theo thứ tự giảm dần theo kích thước
        List<Map.Entry<String, Integer>> sortedGenreList = genreSizeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(4)
                .toList();

        // Tạo danh sách tên và danh sách kích thước tương ứng
        List<String> nameList = sortedGenreList.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Integer> sortedTop4List = sortedGenreList.stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        // Tạo một Map để chứa thông tin kết quả
        Map<String, List<?>> result = new HashMap<>();
        result.put("nameGenre", nameList);
        result.put("top4Genre", sortedTop4List);

        return result;
    }

    public int countSubscriptionCurrent(){
        return advertismentDAO.countSubscriptionCurrent() + userTypeDAO.countSubscriptionCurrent();
    }

    public Object top1Country(){
        return accountDAO.getTop1Country();
    }

    public int countAllAccount(){
        return accountDAO.countAll();
    }

}
