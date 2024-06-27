package cn.ruanwenjun.utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 数字验证码生成程序
 * 
 * 
 * 
 */
public class CheckNumberImgServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int width = 200;
		int height = 50;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		// 创建随机数生成器
		Random random = new Random();

		// 设置背景色
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, width, height);

		// 设置字体
		g.setFont(new Font("Arial", Font.BOLD, 36));

		// 生成随机验证码
		String captcha = generateCaptcha();

		// 将验证码内容保存session
		request.getSession().setAttribute("checkcode_session", captcha);

		g.setColor(new Color(0, 0, 0));
		g.drawString(captcha, 20, 40);

		// 添加干扰线
		for (int i = 0; i < 10; i++) {
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(width);
			int y2 = random.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}

		// 输出图片
		try {
			ImageIO.write(image, "jpg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private static String generateCaptcha() {
		// 生成随机验证码
		Random random = new Random();
		StringBuilder captcha = new StringBuilder();
		String chars = "0123456789";

		for (int i = 0; i < 4; i++) {
			captcha.append(chars.charAt(random.nextInt(chars.length())));
		}

		return captcha.toString();
	}



}
