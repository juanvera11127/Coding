using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Block : MonoBehaviour {
    Rigidbody rb;
    private float speed;
    public static float count = 0;
    private int level;
    private bool isLeft;
    private bool isFirst;

    // Use this for initialization
    void Start () {
        rb = gameObject.GetComponent<Rigidbody>();
        rb.useGravity = false;
        Reposition();
        if(Player.hasStarted)
        {
            level = 1;
            speed = Player.MIN_SPEED;
        }
        else
        {
            level = Player.level;
        }
        if(Player.level > 4)
        {
            if (Random.Range(-1, 1) > 0)
            {
                rb.velocity = new Vector3(2, 0, -Player.speed);
            }
            else
            {
                rb.velocity = new Vector3(-2, 0, -Player.speed);
            }
        }
	}
	
	// Update is called once per frame
	void Update () {
        if(level > Player.level)
        {
            Destroy(this);
        }
        speed = Player.speed;
     
         if(!Player.isStrong)
        {
         if(Player.level > 4)
         {
                if(isLeft && isFirst)
                {
                    isFirst = false;
                    rb.AddForce(1, 0, 0);
                }
            if(rb.position.x > 2)
            {
                isLeft = true;
                rb.AddForce(new Vector3(-2, 0, 0));
            }
            else if(rb.position.x < -2)
            {
                rb.AddForce(new Vector3(2, 0, 0));
            }

            rb.position = new Vector3(rb.position.x, -7.5f + Mathf.Abs(rb.position.x / 5), rb.position.z);
        }
        else
        {
        rb.velocity = new Vector3(0, 0, -speed);
        }

        }
		if(rb.position.z <= -10f || rb.position.y < -20f)
        {
            Player.speed += 2/Player.level;
            count++;
            Reposition();
        }
        rb.rotation = Quaternion.Euler(0, 0, rb.position.x * 12);

    }

    void Reposition()
    {
        float rand = Random.Range(-2, 2);
        rb.rotation = Quaternion.Euler(0, 0, rand * 12);
        rb.position = new Vector3(rand, -7.5f + Mathf.Abs(rand / 5), 45f + (25 * (level-1)) / Player.level);
    }

}
