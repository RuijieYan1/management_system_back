package com.yxtl.business.controller.first;


import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.service.first.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author px
 * @since 2021-11-01
 */
@Api(value = "消息管理", tags = {"消息管理"})
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @ApiOperation(value = "未读消息", notes = "未读列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/unReadMsg")
    public R unReadMessage(@RequestParam Integer currentPage) {
        return messageService.unReadMessage(currentPage);
    }

    @ApiOperation(value = "已读消息", notes = "已读消息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/readMsg")
    public R readMessage(@RequestParam Integer currentPage) {
        return messageService.readMessage(currentPage);
    }

    @ApiOperation(value = "标记单个消息", notes = "标记单个消息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/check")
    public R checkStatus(@RequestParam Integer id) {
        return messageService.checkStatus(id);
    }

    @ApiOperation(value = "标记全部消息", notes = "标记全部消息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/checkAll")
    public R checkAllStatus() {
        return messageService.checkAllStatus();
    }

    @ApiOperation(value = "删除消息", notes = "删除消息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/delMsg")
    public R deleteMessage(@RequestParam Integer id) {
        return messageService.deleteMessage(id);
    }

    @ApiOperation(value = "清空全部消息", notes = "清空全部消息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/clearAll")
    public R clearAllStatus() {
        return messageService.clearAllStatus();
    }

    @ApiOperation(value = "回收站消息", notes = "回收站消息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/recycleMsg")
    public R recycleMessage(@RequestParam Integer currentPage) {
        return messageService.recycleMessage(currentPage);
    }

    @ApiOperation(value = "还原消息", notes = "还原消息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/recoverMsg")
    public R recoverMessage(@RequestParam Integer id) {
        return messageService.recoverMessage(id);
    }

}

