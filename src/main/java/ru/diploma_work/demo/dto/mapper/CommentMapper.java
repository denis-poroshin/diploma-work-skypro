package ru.diploma_work.demo.dto.mapper;

import org.springframework.stereotype.Component;
import ru.diploma_work.demo.dto.CommentDTO;
import ru.diploma_work.demo.dto.CommentsDTO;
import ru.diploma_work.demo.dto.CreateOrUpdateCommentDTO;
import ru.diploma_work.demo.model.CommentModel;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CommentMapper {

    public CommentDTO mapCommentModelToCommentDTO (CommentModel commentModel) {
        CommentDTO properties = new CommentDTO();
        properties.setAuthor(commentModel.getUser().getId());
        properties.setAuthorImage(commentModel.getUser().getImage());
        properties.setAuthorFirstName(commentModel.getUser().getFirstName());
        properties.setCreatedAt(commentModel.getCreatedAt().getTime());
        properties.setPk(commentModel.getId());
        properties.setText(commentModel.getText());
        return properties;
    }

    public CommentsDTO mapListCommentModelToCommentsDTO (List<CommentModel> commentsList) {
        List<CommentDTO> dtoList;
        dtoList = commentsList.stream()
                .map(this::mapCommentModelToCommentDTO)
                .collect(Collectors.toUnmodifiableList());
        CommentsDTO properties = new CommentsDTO();
        properties.setCount(dtoList.size());
        properties.setResults(dtoList);
        return properties;
    }

    public void mapCreateOrUpdateCommentDTOToCommentModel (CommentModel commentModel, CreateOrUpdateCommentDTO properties) {
        commentModel.setText(properties.getText());
    }
}
